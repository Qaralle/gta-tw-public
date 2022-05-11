import React, { Component} from 'react';
import { Animated, View } from 'react-native';

import ReactDOM from 'react-dom';


export class Creditionals_message extends Component {

	 constructor(props) {
        super(props);

        this.state = {
            messages: "",
        }
        this.fadeAnimation = new Animated.Value(1);
    }


    componentDidUpdate() {
        Animated.loop(
            Animated.sequence([
                Animated.timing(this.fadeAnimation, {
                    toValue: 0,
                    duration: this.props.duration,
                    useNativeDriver: true,
                }),
                Animated.timing(this.fadeAnimation, {
                    toValue: 1,
                    duration: this.props.duration,
                    useNativeDriver: true,
                })
            ]),
            {
                iterations: this.props.repeat_count
            }
        ).start();
    }

	show(value) {
		this.setState({messages: value});
	}

	hide() {
		this.setState({messages: ""});
	}

	render() {
        return (
        	<div className="Creditionals-message">
				{	
					this.state.messages !== "" && 
					<Animated.View style={{ opacity: this.fadeAnimation }}>
						<span>*{this.state.messages}</span>
					</Animated.View>
				}
			</div>
        );
    }
}

export default Creditionals_message;