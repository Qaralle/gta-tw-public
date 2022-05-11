import { createStore } from 'redux';
import React from "react";

const initialState = {
    token: '',
    rtoken: ''
}

function reducer(state, action) {
    switch (action.type) {
        case "TOKEN_CLEAR":{
            sessionStorage.removeItem("rtoken");
            return { ...state, token: '', rtoken: ''};
        }

        case "TOKEN_UPDATE":
            return { ...state, token: action.token};

        case "NEW_TOKEN":
            sessionStorage.setItem("rtoken", action.rtoken);
            return { ...state, token: action.token, rtoken: action.rtoken};
        default:
            return state;
    }

}

const tstore = createStore(reducer, initialState);

export default tstore