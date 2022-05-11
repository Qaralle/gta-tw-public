import React, {useState, useEffect} from 'react';

import Login from './Login';
import Register from './Register';
import Main from './Main-Page'

import AccountContext from './storage/account_context';
import tstore from './storage/token_storage';




import Main_page_hero from './Main-page-hero';

function App() {

	let [accState, setAccState] = useState('signIn');
	let [flag, setFlag] = useState(false)

	const switchToSignIn = () => {
		setAccState("signIn");
	};
	const switchToSignUp = () => {
		setAccState("signUp");
	};
	const switchToMain = () => {
		setAccState("goMain");
	};	

	const contextValue = {switchToSignIn, switchToSignUp, switchToMain};

	useEffect(() => {
		console.log(sessionStorage.getItem("rtoken"))
		console.log(tstore.getState().token)

		if(tstore.getState().token === '' && sessionStorage.getItem("rtoken") !== null){
			console.log("boobs")

			fetch("/api/refresh/token", {
				method: 'POST',
				headers: {'Content-Type': 'application/json'},
				body: JSON.stringify({refresh_token: sessionStorage.getItem("rtoken")})
			}).then(response => response.json().then(data => {
				if (response.ok) {
					console.log(data)
					tstore.dispatch({type: "NEW_TOKEN", token: data.token, rtoken: data.refresh_token})
					console.log(tstore.getState())
					setFlag(true)
					setAccState('main');

				} else {
					alert("sosi")
				}
			}))
		}else {
			setFlag(true)
		}
	},[]);





















	return (
		<AccountContext.Provider value={contextValue}>
			<div className="App">
				{flag && accState === "signIn" && <Login/>}
				{flag && accState === "signUp" && <Register />}
				{flag && tstore.getState().token !== '' && <Main />}
			</div>
		</AccountContext.Provider>
	);

}

export default App;