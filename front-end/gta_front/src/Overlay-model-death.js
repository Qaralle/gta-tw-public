import React, { Component} from 'react';
import { TransitionGroup } from 'react-transition-group';
import { CSSTransition } from 'react-transition-group';
import ReactDOM from 'react-dom';

import './Overlay-model-death.css';

export class Death_overlay extends Component {


	 constructor(props) {
        super(props);

        this.state = {
            messages: false,
        }
    }

	show() {
		this.setState({messages: true});
	}

	hide() {
		this.setState({messages: false});
	}

	render() {
        return (
				<div className="Death-overlay">
					<CSSTransition in={this.state.messages} timeout={500} classNames="item" unmountOnExit >
						<div className="Modal-overlay">
							<div className="Modal-window">
								<div className="Modal-titlebar">
									<p>WASTED</p>
								</div>
								<div className="Modal-content">
									<a onClick={()=>{this.hide()}}>back to map-></a>
								</div>
							</div>
						</div>
					</CSSTransition>
				</div>
        );
    }
}

export default Death_overlay;