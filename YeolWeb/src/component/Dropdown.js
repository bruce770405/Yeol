import React, { Component } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';



/**
 * 下拉選單Component.
 * @author BruceHsu
 * @version
 * @since
 * @see
 */
export class Dropdown extends Component {

  constructor(props) {
    super(props);

    this.state = {
      dropdownValue: 0
    }
  }

  /**
   * life cycle.
   */
  componentDidMount() {
    console.log('Dropdown componentDidMount');
    const { obj } = this.props
    const keys = Object.keys(obj)

    this.setState({
      dropdownValue: obj[keys[0]].val
    })
  }

  /**
   * 處理異動資料往外傳.
   * @param {事件} event 
   */
  handleChange = event => {
    console.log('event.target.value', event.target.value)
    this.setState({
      dropdownValue: event.target.value
    })

    this.props.eventFunction(event.target.value)
  }

  render() {

    const { title, obj } = this.props
    const keys = Object.keys(obj)

    return (

        <FormControl variant="filled" className={classes.formControl}>

          {
            title ? <InputLabel>{title}</InputLabel> : null
          }

          <Select value={this.state.dropdownValue} onChange={this.handleChange} >

            return ({
              keys.map(key => (
                <MenuItem key={obj[key].name} value={obj[key].val}>{obj[key].name}</MenuItem>
              ))
            })
           </Select>

        </FormControl>
    )
  };
}


/** css. */
const classes = makeStyles(theme => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 150,
  },
}));
