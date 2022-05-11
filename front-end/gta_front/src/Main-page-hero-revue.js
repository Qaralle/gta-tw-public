import React, {useState, useEffect, useContext} from 'react';
import ReactDOM from 'react-dom';

import Revue_district from './Revue-district';
import Revue_factory from './Revue-factory';

import './Main-page-hero-revue.css';
import RevueContext from './storage/revue_context';



function Main_page_hero_revue(props) {
	let [gg,setgg] = useState(props.isLookingAt);

	useEffect(()=> {
		console.log("main_page_didMount");
	},[props]);

	return(

			<div id="main-page-hero-revue">
				{props.distrParams &&
					<div id="main-page-hero-revue-adge">
						{props.isLookingAt !== -1 && <Revue_district />}
						{props.isLookingAt !== -1 && props.distrParams.fact && !props.distrParams.isEnemy && <Revue_factory /> }
					</div>
				}
			</div>

		);
}

export default Main_page_hero_revue;