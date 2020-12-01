import React, { Component, PropTypes } from 'react' // eslint-disable-line no-unused-vars
import { Row } from 'react-bootstrap' // eslint-disable-line no-unused-vars
import { Button } from '../cus.button/button' // eslint-disable-line no-unused-vars

import './dialog.component.css'

const defaultProps = {
  show: false,
  mask: false,
  title: '',
  zIndex: 1000,
  click: () => { },
  onCancel: () => { }
}

/**
 * common - dialog
 * @author BruceHsu
 */
export class Dialog extends Component {
  constructor (props) {
    super(props)
    this.state = {
      show: props.show,
      mask: props.mask
    }
  }

  componentWillReceiveProps (nextProps) {
    this.setState({ show: nextProps.show })
  }

  render () {
    const { title, children, click, onCancel } = this.props
    const { show } = this.state

    return (
      show ?
        <div>

          {
            <div className="mask" />
          }

          <div className="m-dialog" style={{ zIndex: 1 }}>
            <div className="md-dialog">

              <div className="md-dialog-title">
                <h4>{title}</h4>
                <span className="closeBtn">
                  <i className="iconfont" onClick={() => this.setState({ show: false })}>&times;</i>
                </span>
              </div>

              <div className="md-dialog-content">
                {children}
              </div>

              <Row className="show-grid fixBtn">
                <Button
                  className="btn btn-default btn-md"
                  type="button"
                  onClick={onCancel.bind(this)}>
                  取消
                </Button>
                <span style={{padding: 5}}></span>
                <Button
                  className="btn btn-primary btn-md"
                  type="button"
                  onClick={click.bind(this)}>
                確定
                </Button>
              </Row>

            </div>
          </div>
        </div > : null
    )
  }
}

Dialog.defaultProps = defaultProps
