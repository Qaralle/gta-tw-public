import React, { useContext } from 'react';
import ReactDOM from 'react-dom';

import RevueContext from './storage/revue_context';

import industry from './industry.png';

function Revue_district(props) {
	const { handleWorkFetch } = useContext(RevueContext);
	const { distrParams } = useContext(RevueContext);

	function handleWork() {
		handleWorkFetch();
	}

	return(
		<div className="Revue-panel" id="revue-factory-panel">
			<div className="Revue-panel-header" id="revue-factory-panel-header">
				<img src={industry} alt="Industry"/>
				<a>Factory</a>
				<a>{distrParams.factName}</a>
			</div>
			<div className="Revue-panel-hero" id="Revue-factory-hero">
				<button className="Button" type="button" onClick={handleWork}>Work</button>
			</div>
		</div>
		);
}

export default Revue_district;