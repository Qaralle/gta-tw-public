import React, {useState, useEffect} from 'react';
import { intervalToDuration, isBefore} from "date-fns";
import {useTicker} from './useTicker.js'

function Timer(props) {
	let parst = props.time.split(":");
	let now = new Date();
	const[futureDate, setFutureDate] = useState(new Date(now.getFullYear(), now.getMonth(), now.getDate(), now.getHours() + parseInt(parst[0], 10), now.getMinutes() + parseInt(parst[1], 10), now.getSeconds() + parseInt(parst[2], 10)));

	const {hours, minutes, seconds, isTimeUp} = useTicker(futureDate);

	if(isTimeUp) {
		props.timeUpCallBack();
	}

	return(
		<div>
			{
				!isTimeUp && <span>{hours >= 10 ? hours : `0${hours}`}:{minutes >= 10 ? minutes : `0${minutes}`}:{seconds >= 10 ? seconds : `0${seconds}`}</span>
			}
		</div>
	);
}

export default Timer;