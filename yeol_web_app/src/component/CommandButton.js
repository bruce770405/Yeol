import React from "react";
import Fab from '@material-ui/core/Fab';
import { ButtonColor } from './type/ButtonType';
/**
 * 按鈕Component.
 * @author BruceHsu
 * @version
 * @since
 * @see
 * @param {*} props 
 */
const CommandButton = (props) => {
  const { color = ButtonColor.PRIMARY, eventFunction } = props;
  return <Fab variant="contained" color={color} onClick={eventFunction}> {props.children}</Fab>;
};

export default CommandButton;
