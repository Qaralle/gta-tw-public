import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';


function Revue_district_move(props) {
	return(
		<div className="Move">
			<button className="Button" type="button" onClick={props.callBackMove}>
				Move To
			</button>
		</div>
		);
}

export default Revue_district_move;