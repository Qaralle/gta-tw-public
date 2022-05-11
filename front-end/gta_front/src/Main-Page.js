import React, {useEffect, useState, useRef} from 'react';
import * as SockJS from 'sockjs-client';
import {Stomp} from "@stomp/stompjs";

import Main_page_header from './Main-page-header';
import Main_page_hero from './Main-page-hero';
import Death_overlay from './Overlay-model-death';


import './Main-Page.css';
import tstore from "./storage/token_storage";
import uStorage from "./storage/user_storage";
import sstore from "./storage/socket_storage";


import MainPageContext from './storage/MainFrameContext';

function Main_page() {
	const deathRef = useRef(Death_overlay);

	let [userParams, setUserParams] = useState({futureDate: '00:00:00', location: 0, damage: 0, protect: 0, heal: 0});
	let [gangColor, setGangColor] = useState();
	let [headerValues, setHeaderValues] = useState({workCD:"00:00:00", moveCD: "00:00:00", CaptureCD: "00:00:00"});

	let stompClient = null;

	const colors = {
		GREEN: "#13e200",
		RED: "#e20000",
		PURPLE: "#e200b5",
		YELLOW:	"#e2c800"
	}


	function userParamsSet() {


		function youFetch(){
			return fetch('/api/app/you', {
				headers: {
					'Content-Type': 'application/json;charset=utf-8',
					'Authorization': `Bearer_${tstore.getState().token}`
				},
			})
		}

		function okResponse(resp){
			let heal = resp.data.characteristics["HEAL"]

			let now = new Date();

			setUserParams({
				futureDate: resp.data.deathTime === null ? "00:00:00" : resp.data.deathTime,
				location: resp.data.areaunitEntity.id,
				damage: resp.data.characteristics["DAMAGE"],
				protect: resp.data.characteristics["PROTECT"],
				heal:  heal === null?0:heal	});

			uStorage.dispatch({type:"SET_USER_PARAMS",
				name:resp.data.name,
				sName:resp.data.lastname,
				banditId:resp.data.id,
				gangId:resp.data.gangByGangId.id});

			console.log("User location after update: " + userParams.location);
			let id = uStorage.getState().gangId;
			if(id == 1) {
				setGangColor(colors.YELLOW);
			}else if(id == 2) {
				setGangColor(colors.PURPLE);
			}else if(id == 3) {
				setGangColor(colors.GREEN);
			}else {
				setGangColor(colors.RED);
			}

			if(resp.data.isknockdown !== "00:00:00") {
				console.log("Knock down time: " + resp.data.deathTime);
				deathRef.current.show();
			}
		}

		youFetch().then(response => response.text().then(text => {
			let resp = JSON.parse(text)
			if (response.ok) {
				okResponse(resp)
			}else if (response.status === 403) {
				fetch("/api/refresh/token", {
					method: 'POST',
					headers: {'Content-Type': 'application/json'},
					body: JSON.stringify({refresh_token: tstore.getState().rtoken})
				}).then(response => response.json().then(rtoken => {
					if (response.ok) {
						tstore.dispatch({type: "TOKEN_UPDATE", token: rtoken.token})
						youFetch.then(response => response.text().then(text => {
							if (response.ok) {
								okResponse(resp)
							} else {
								alert("suck");
							}
						}))
					}
				}))
			}
		}))
	}


	useEffect(() => {

		console.log(sstore.getState().characteristic_socket)
		if (sstore.getState().characteristic_socket != null){
			sstore.getState().characteristic_socket.close()
		}

		let socket = new SockJS("/api/app/notifications");
		sstore.dispatch({type:"NEW_CHARACTER", characteristic_socket:socket})
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			console.log('Connected: ' + frame);
			stompClient.subscribe('/notification/characteristic', function(greeting){
				console.log(JSON.parse(greeting.body).content);
				userParamsSet();
			});
		});


		userParamsSet();

		console.log(userParams);
	},[]);

	useEffect(()=>{
		console.log("User parametrs were updated");
	},[userParams]);

	const mainFrameContext = { headerValues , setHeaderValues};

	return(
		<MainPageContext.Provider value={mainFrameContext}>
			<div id='main-back-plug'>
				{
					userParams.location !== 0 &&
					<div className='Main'>
						<Death_overlay ref={deathRef}/>
						<Main_page_header userParams={userParams} gangColor={gangColor} mainHeroValues={headerValues}/>
						<Main_page_hero banditId={userParams.banditId} banditLocation={userParams.location}/>
						<div id="main-page-footer">
						</div>
					</div>
				}
			</div>
		</MainPageContext.Provider>
		);
}

export default Main_page;