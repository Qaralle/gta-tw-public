import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';

import industry from './industry.png';

function Map_cell(props) {

	let [cellState, setCellState] = useState(false);
	let [resSyle, setResStyle] = useState({});

	let locationColorStyle = {
		backgroundColor: props.color,
		opacity: 0.7,
		
		border: "solid",
		borderColor: "#000000",
		borderWidth: "4px",
	}
	let clickedColorStyle = {
		backgroundColor: props.color,
		opacity: 0.7,

		border: "solid",
		borderColor: "#ffffff",
		borderWidth: "4px",
	}
	let freeColorStyle = {
		backgroundColor: props.color,
		opacity: 0.7
	}

	function switchState() {
		setCellState(false);
	}

	function handleClick() {
		if(!cellState) {
			setCellState(true);
			props.callBackF({id: props.id, stateCallBack: switchState});
		}
	}

	function positioned() {
		if(cellState) {
			setResStyle(clickedColorStyle);
			return;
		}else if(props.inPosition) {
			setResStyle(locationColorStyle);
			return;
		}else {
			setResStyle(freeColorStyle);
			return;
		}
	}

	useEffect(() => {
		console.log("cell " + props.id + ": in pos: " + props.inPosition);
		positioned();
	},[cellState, props]);

	return(
		<div style={resSyle} className="Map-cell" onClick={handleClick}>
			{props.factory && <img src={industry} alt="Industry"/>}
			<a>{props.id}</a>
		</div>
		);
}

export default Map_cell;