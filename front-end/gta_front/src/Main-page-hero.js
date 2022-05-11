import React, {useState, useEffect, useContext} from 'react';
import ReactDOM from 'react-dom';

import * as math from 'mathjs'

import Main_page_hero_revue from './Main-page-hero-revue';
import Main_page_hero_map from './Main-page-hero-map';


import './Main-page-hero.css';
import RevueContext from './storage/revue_context';

import uStorage from './storage/user_storage'
import * as SockJS from "sockjs-client";
import {Stomp} from "@stomp/stompjs";
import tstore from "./storage/token_storage";
import sstore from "./storage/socket_storage";
import {Messages} from "primereact/messages";
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import "primereact/resources/themes/lara-light-indigo/theme.css"
import "primereact/resources/primereact.min.css"

import MainPageContext from './storage/MainFrameContext';

let MessagesInstance
function Main_page_hero(props) {


	function createFetch(fetchType, okResp, NotOkResp){
		fetchType().then(response => response.text().then(text => {
			if (response.ok) {
				let resp = JSON.parse(text)
				okResp(resp)
			}else if (response.status === 403) {
				fetch("/api/refresh/token", {
					method: 'POST',
					headers: {'Content-Type': 'application/json'},
					body: JSON.stringify({refresh_token: tstore.getState().rtoken})
				}).then(response => response.json().then(rtoken => {
					if (response.ok) {
						tstore.dispatch({type: "TOKEN_UPDATE", token: rtoken.token})
						fetchType().then(response => response.text().then(text => {
                            let resp = JSON.parse(text)
							if (response.ok) {
                      			okResp(resp)
							} else {
								NotOkResp(text);
							}
						}))
					}
				}))
			}
		}))
	}

	let stompClient = null;

	let [revueState, setRevueState] = useState('');
	let [distrParams, setDistrParams] = useState({id: -1, banditNum: 0, gangId: 0, gangColor:"", gangName:"", damage: 0, protect: 0, fact:"", factName: '', isRespawn: "", isEnemy: ""});
	let [userCellVue, setUserCellVue] = useState(-1);
	let [map, setMap] = useState([]);
	let [cellSwitchStateCallBack, setCellSwitchStateCallBack] = useState({callback:()=>{}});
	let [userPosition, setUserPosition] = useState(props.banditLocation);
	let [probability, setProbability] = useState(0);
	let [message, setMessage] = useState("");
	let [timeKD, setTimeKD] = useState("00:00:00");
	let {setHeaderValues} = useContext(MainPageContext);

	useEffect(()=>{
		setKdTime();
	},[]);

	function setKdTime(){
		function kdFetch(){
			return fetch('/api/app/kd', {
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			setHeaderValues({workCD:resp.data.kdWorkTime, moveCD:resp.data.kdMoveTime, captureCD:resp.data.kdCaptureTime});
		}

		function notOkResponse(resp){
			MessagesInstance.replace({
				severity: 'error',
				summary: "wrong refresh token",
				closable: false,
			})
		}

		createFetch(kdFetch,okResponse,notOkResponse)


	}

	function setMapState() {


		function mapFetch(){
			return fetch('/api/app/map', {
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			// console.log(resp.data.map)
			setMap(resp.data.map)
		}

		function notOkResponse(resp){
			MessagesInstance.replace({
				severity: 'error',
				summary: "wrong refresh token",
				closable: false,
			})
		}

		createFetch(mapFetch,okResponse,notOkResponse)

	}



	useEffect(()=>{
		mapRenderCellInfo()
		setUserPosition(props.banditLocation);
	},[map])


	useEffect(() => {

		console.log(sstore.getState().map_socket)
		if (sstore.getState().map_socket != null){
			sstore.getState().map_socket.close()
		}


		let socket = new SockJS("/api/app/notifications");
		sstore.dispatch({type:"NEW_MAP", map_socket:socket})
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			console.log('Connected: ' + frame);
			stompClient.subscribe('/notification/map', function(greeting){
				console.log(JSON.parse(greeting.body).content);
				setMapState();
			});
		});

		setMapState();
	},[]);




	const switchToPosition = () => {
		setRevueState('position');
	};
	const switchToMove = () => {
		setRevueState('move');
	};
	const switchToCapture = () => {
		setRevueState('capture');
	};

	function getCaptureProbability(params) {

		function probFetch(){
			return fetch(`/api/app/count_prob/${params}`, {
				method: "GET",
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			setProbability(resp.data);
		}

		function notOkResponse(resp){
			MessagesInstance.replace({
				severity: 'error',
				summary: "wrong refresh token",
				closable: false,
			})
		}

		createFetch(probFetch,okResponse,notOkResponse)
	}

	const handleCaptureFetch = () => {

		function captFetch(){
			return fetch('/api/app/capture', {
				method: "POST",
				body:JSON.stringify({"defense_id": userCellVue}),
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			if(!resp.result){
				MessagesInstance.replace({
					severity: 'error',
					summary: "Capture failed",
					closable: false,
				})
			}else {
				setKdTime();
				MessagesInstance.replace({
					severity: 'success',
					summary: "Captured",
				closable: false,
				})
			}
		}

		function notOkResponse(resp){
			MessagesInstance.replace({
				severity: 'error',
				summary: "wrong refresh token",
				closable: false,
			})
		}

		createFetch(captFetch,okResponse,notOkResponse)

	};

//    private int defense_id;
//     private int person;

	const handleMoveToFetch = () => {

		function moveFetch(){
			return  fetch('/api/app/run_over', {
				method: "POST",
				body:JSON.stringify({"destination": userCellVue}),
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			console.log(resp.result)

			if (resp.result){
				setUserPosition(userCellVue)
			}

			if(!resp.result){
				MessagesInstance.replace({
					severity: 'error',
					summary: "Move in cooldown",
				closable: false,
				})
			}else {
				setKdTime();
				MessagesInstance.replace({
					severity: 'success',
					summary: "Moved",
				closable: false,
				})
			}

		}

		function notOkResponse(resp){
			MessagesInstance.replace({
				severity: 'error',
				summary: "wrong refresh token",
				closable: false,
			})
		}

		createFetch(moveFetch,okResponse,notOkResponse)
		setKdTime()
	};


	const handleWorkFetch = () => {

		function workFetch(){
			return fetch('/api/app/work', {
				method: "POST",
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			if(!resp.result){
				MessagesInstance.replace({
					severity: 'error',
					summary: "Work in cooldown",
				closable: false,
				})
			}else {
				setKdTime();
				MessagesInstance.replace({
					severity: 'success',
					summary: "Worked",
				closable: false,
				})
			}
		}

		function notOkResponse(resp){
			MessagesInstance.replace({
				severity: 'error',
				summary: "wrong refresh token",
				closable: false,
			})
		}

		createFetch(workFetch,okResponse,notOkResponse)
	}

	function mapCellClickVue(params) {
		console.log("clicked on cell:" + params);
		if(params.id !== userCellVue) {
			cellSwitchStateCallBack.callback();
			setUserCellVue(params.id);
			setCellSwitchStateCallBack({callback: params.stateCallBack});
			console.log(params.id);
		}
	}

	function mapRenderCellInfo() {

		for(var i = 0; i < map.length; i++) {

			if(userCellVue === i + 1) {
				setDistrParams({
					distName: map[i].block.gangByGangId != null ? map[i].block.gangByGangId.name : "",
					peopleNum: map[i].block.bandits.length,
					damage: map[i].block.characteristics.DAMAGE != null ? map[i].block.characteristics.DAMAGE : 0,
					protect: map[i].block.characteristics.PROTECT != null ? map[i].block.characteristics.PROTECT : 0,
					fact: map[i].workshopEbtity != null,
					factName: map[i].workshopEbtity != null ? map[i].workshopEbtity.name : "",
					isRespawn: map[i].block.isrespawn,
					isEnemy: map[i].block.gangByGangId != null ? uStorage.getState().gangId !== map[i].block.gangByGangId.id : true
				});
			}
		}
	}

	function mapRenderInfoHook() {

		if(userCellVue !== -1);
		mapRenderCellInfo(userCellVue);
	}

	useEffect(() => {

		//получить карту
		// fetch()
		if(userCellVue !== -1) {
			mapRenderCellInfo();
		}

	},[userCellVue]);

	function is_near(a,b){
		a=a++
		b=b++
		console.log(a)
		console.log(b)
		if ((a%8)===0 && ((b-1)%8)===0){
			return false;
		} else if ((b%8)===0 && ((a-1)%8)===0){
			return false;
		}

		if (math.abs(a-b)===1){
			return true
		}else if (a+8 === b || a-8 === b){
			return true
		}else if (a+8-1 === b || a+8+1===b){
			return true;
		}else if (a-8-1 === b || a-8+1===b){
			return true;
		}else {
			return false
		}
	}

	useEffect(() => {
		if(distrParams) {
			console.log("Define what to do with cell");
			console.log("my gangId: " + uStorage.getState().gangId);

			console.log("user pos:" + userPosition);
			console.log("isEnemy:" + distrParams.isEnemy);
			if(distrParams.isEnemy) {
				if(!distrParams.isRespawn && is_near(userPosition,userCellVue)) {
					getCaptureProbability(userCellVue);
					
					switchToCapture();
					console.log("capture");
				}else {
					switchToPosition();
					console.log("resp pos");
				}
			}else {
				if(userPosition != userCellVue) {
					switchToMove();
					console.log("move");
				}else {
					switchToPosition();
					console.log("pos");
				}
			}
		}
	},[distrParams]);


	const revueStatValue = { switchToPosition, switchToMove, switchToCapture, revueState, probability, distrParams, handleCaptureFetch, handleMoveToFetch, handleWorkFetch};

	return(
		<RevueContext.Provider value={revueStatValue}>
			<Messages className="p-messages"	icon ref={(el) => MessagesInstance = el}/>
			<div id="main-page-hero">
				{map[0] && <Main_page_hero_map vueCallBack={mapCellClickVue} mapInf={map} userPos={userPosition} isLookingAt={userCellVue}/>}
				<Main_page_hero_revue isLookingAt={userCellVue} distrParams={distrParams}/>
			</div>
		</RevueContext.Provider>
		);
}

export default Main_page_hero;