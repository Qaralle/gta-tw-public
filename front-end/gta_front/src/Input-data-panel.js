import React, {useState, useRef} from 'react';
import ReactDOM from 'react-dom';


function Input_data_panel(props) {
	return(
		<div className="Input-panel">
			<p>{props.hstr}</p>
			<input type={props.typeT} onChange={props.callBack}/>
		</div>
		);
}

export default Input_data_panel;