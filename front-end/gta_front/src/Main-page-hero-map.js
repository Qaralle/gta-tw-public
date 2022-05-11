import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';

import Map_cell from './Map-cell';

function Main_page_hero_map(props) {

	let [renderedMap, setRenderedMap] = useState([]);

	const colors = {
		GREEN: "#13e200",
		RED: "#e20000",
		PURPLE: "#e200b5",
		YELLOW:	"#e2c800"
	}

	function handleOnCellClick(params) {
		props.vueCallBack(params);
	}


	useEffect(() => {
		setRenderedMap(() => {
			let vv = [];
			let col = '';
			let cellCol = '';
			let pos = false;
			let fact = false;


			{for(var i = 0; i <= 63; i++){
				
				if(props.mapInf[i].workshopEbtity != null) {
					fact = true;
				}else {
					fact = false;
				}

				if (props.mapInf[i].block.gangByGangId != null) {
					cellCol = props.mapInf[i].block.gangByGangId.color
				}

				if (cellCol == "GREEN") {
					col = colors.GREEN;
				} else if (cellCol == "RED") {
					col = colors.RED;
				} else if (cellCol == "PURPLE") {
					col = colors.PURPLE;
				} else if (cellCol == "YELLOW") {
					col = colors.YELLOW;
				} else {
					col = "";
				}

				cellCol = "";

				pos = (props.userPos === i+1);

				vv.push(<Map_cell key={i} color={col} id={i+1} factory={fact} inPosition={pos} callBackF={handleOnCellClick}/>);
			}}
			return [vv];
		});
	},[props]);


	return(
		<div className="Main-page-hero-map-frame">
			{renderedMap}
		</div>
		);
}

export default Main_page_hero_map;