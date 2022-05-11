import { createStore } from 'redux';
import React from 'react';

const initialUserState = {
	name: '',
	sName: '',
	banditId: 0,
	gangId: 0
};

function reducer(state, action) {
	switch(action.type) {
		case "SET_USER_PARAMS":
			return { ...state, name:action.name, sName:action.sName, banditId:action.banditId, gangId:action.gangId};
		default: return {...state};
	}
}

const userStore = createStore(reducer, initialUserState);

export default userStore;