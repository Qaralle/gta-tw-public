import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';

import dice from './dice.png';
import industry from './dice.png';

function Revue_district_capture(props) {


	return(
		<div className="Capture">
			<div className="Revue-district-info">
				<img src={industry} alt="Industry"/>
				<a>{props.probability}</a>
			</div>
			<div className="Revue-district-capture-button">
				<button className="Button" type="button" onClick={props.callBackCapture}>
					Capture
				</button>
			</div>
		</div>
		);
}

export default Revue_district_capture;