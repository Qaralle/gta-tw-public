import { createStore } from 'redux';
import React from "react";

const initialState = {
    characteristic_socket: null,
    map_socket: null
}

function reducer(state, action) {
    switch (action.type) {
        case "NEW_CHARACTER":
            return { ...state, characteristic_socket: action.characteristic_socket};
        case "NEW_MAP":
            return { ...state, map_socket: action.map_socket};
        default:
            return state;
    }

}

const sstore = createStore(reducer, initialState);

export default sstore