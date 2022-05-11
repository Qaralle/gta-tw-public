import React, {useState, useEffect, useContext} from 'react';
import ReactDOM from 'react-dom';

import Revue_district_move from './Revue-district-move';
import Revue_district_capture from './Revue-district-capture';

import RevueContext from './storage/revue_context';

import car from './car.png';
import rifle from './rifle.png';
import group from './group.png';
import map from './map.png';


function Revue_district(props) {
	const { handleCaptureFetch } = useContext(RevueContext);
	const { handleMoveToFetch } = useContext(RevueContext);

	function handleMoveTo() {
		handleMoveToFetch();
	}

	function handleCapture() {
		handleCaptureFetch();
	}

	const { revueState } = useContext(RevueContext);
	const { probability } = useContext(RevueContext);
	const { distrParams } = useContext(RevueContext);

	return(
		<div className="Revue-panel" id="revue-district-panel">
			<div className="Revue-panel-header" id="revue-district-panel-header">
				<img src={map} alt="Map"/>
					<a>District</a>
					<a id="name">{distrParams.distName}</a>
			</div>
			<div className="Revue-panel-hero" id="Revue-district-hero">
				<div className="Revue-district-info">
					<img src={group} alt="Group"/>
					<a>{distrParams.peopleNum}</a>
				</div>
				<div className="Revue-district-info">
					<img src={rifle} alt="Rifle"/>
					<a>{distrParams.damage}</a>
				</div>
				<div className="Revue-district-info">
					<img src={car} alt="Car"/>
					<a>{distrParams.protect}</a>
				</div>
			</div>
			{ revueState === 'move' && <Revue_district_move callBackMove={handleMoveTo} />}
			{ revueState === 'capture' && <Revue_district_capture callBackCapture={handleCapture} probability={probability}/>}
		</div>
		)
}

export default Revue_district;