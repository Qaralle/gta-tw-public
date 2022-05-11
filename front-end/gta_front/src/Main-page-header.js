import React, {useState, useEffect, useContext} from 'react';
import ReactDOM from 'react-dom';

import Timer from './Timer'

import './Main-page-header.css';
import tstore from './storage/token_storage';
import AccountContext from './storage/account_context';
import userStore from './storage/user_storage';


import MainPageContext from './storage/MainFrameContext';


import car from './car.png';
import rifle from './rifle.png';
import med from './medicine-box.png';
import skull from './human-skull.png';
import work from './fist.png';
import move from './run.png';
import capture from './swords.png';

function Main_page_header(props) {
	
	let { headerValues } = useContext(MainPageContext);

	const[isTimeUp,setIsTimeUp] = useState();
	const[workTimeUp, setWorkTimeUp] = useState();
	const[moveTimeUp, setMoveTimeUp] = useState();
	const[captureTimeUp, setCaptureTimeUp] = useState();

	function timeUp() {
		setIsTimeUp(false);
	}
	function workTimeUpCallBack() {
		setWorkTimeUp(false);
	}
	function moveTimeUpCallBack() {
		setMoveTimeUp(false);
	}
	function captureTimeUpCallBack() {
		setCaptureTimeUp(false);
	}

	let gangStyle = {
		background: `linear-gradient(#1D2026, ${props.gangColor} 370%)`,
	}

	const { switchToSignIn } = useContext(AccountContext);


	function handleSignOut() {
		tstore.dispatch({type: "TOKEN_CLEAR"});
		switchToSignIn();
	}

	useEffect(()=> {
		if(props.userParams.futureDate !== "00:00:00") setIsTimeUp(true)
	},[props]);

	useEffect(()=> {
		console.log("YA DAUN I YA ZAPUSTIL TAYMER");
		console.log(headerValues);
		if(headerValues.workCD !== "00:00:00") setWorkTimeUp(true)
		if(headerValues.moveCD !== "00:00:00") setMoveTimeUp(true)
		if(headerValues.CaptureCD !== "00:00:00") setCaptureTimeUp(true)
	},[headerValues]);
	
	return(
		<div id="main-page-header">
			<div id="main-page-header-params">
				<div style={gangStyle} className="user-characateristic" id="gun">
					{isTimeUp ? <Timer time={props.userParams.futureDate} timeUpCallBack={()=>{timeUp()}}/> : <span>Alive</span>} 
					<img src={skull} alt="skull"/>
				</div>
				<div style={gangStyle} className="user-characateristic" id="gun">
					<a>{props.userParams.damage}</a>
					<img src={rifle} alt="Rifle"/>
				</div>
				<div style={gangStyle} className="user-characateristic" id="car">
					<a>{props.userParams.protect}</a>
					<img src={car} alt="Car"/>
				</div>
				<div style={gangStyle} className="user-characateristic" id="med">
					<a>{props.userParams.heal}</a>
					<img src={med} alt="Med"/>
				</div>
				<div style={gangStyle} className="user-characateristic" id="work">
					{workTimeUp ? <Timer time={headerValues.workCD} timeUpCallBack={()=>{workTimeUpCallBack()}}/> : <span>Ready</span>} 
					<img src={work} alt="work"/>
				</div>
				<div style={gangStyle} className="user-characateristic" id="move">
					{moveTimeUp ? <Timer time={headerValues.moveCD} timeUpCallBack={()=>{moveTimeUpCallBack()}}/> : <span>Ready</span>} 
					<img src={move} alt="move"/>
				</div>
				<div style={gangStyle} className="user-characateristic" id="capture">
					{captureTimeUp ? <Timer time={headerValues.captureCD} timeUpCallBack={()=>{captureTimeUpCallBack()}}/> : <span>Ready</span>} 
					<img src={capture} alt="capture"/>
				</div>
			</div>
			<div id="main-page-header-button">
				<button className="Button" type="button" onClick={handleSignOut} >
					Sign Out
				</button>
			</div>
		</div>
		);
}

export default Main_page_header;