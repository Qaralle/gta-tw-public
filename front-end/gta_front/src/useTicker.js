import React, {useState, useEffect} from 'react';
import { intervalToDuration, isBefore} from "date-fns";

export const useTicker = (futureDate) => {
	const[now, setNow] = useState(new Date());
	
	let hours = 0;
	let minutes = 0;
	let seconds = 0;
	let millis = 0;

	useEffect(() => {
		const interval = setInterval(()=> {
			console.log('Ticking');
			setNow(new Date());
		},1000);

		return () => {
			clearInterval(interval);
		};
	}, [futureDate]);

	const isTimeUp = isBefore(futureDate, now);

	if(!isTimeUp){

		let duration = intervalToDuration({
			start: now,
			end: futureDate,
		})

		console.log(now);
		console.log(futureDate);
		hours = duration.hours;
		minutes = duration.minutes;
		seconds = duration.seconds;

		console.log(hours + ":" + minutes +":"+ seconds +": "+ isTimeUp)
	}

	return {hours: hours, minutes: minutes, seconds: seconds, isTimeUp: isTimeUp};
}