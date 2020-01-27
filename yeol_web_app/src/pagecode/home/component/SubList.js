import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { red } from '@material-ui/core/colors';
import { CardComponent } from '../../../component/CardComponent';

/**
 * 畫面卡片式元件
 * @author BruceHsu
 * @version
 * @since
 */
export const SubList = (props) => {

  const classes = useStyles();
  
  return (
    <CardComponent>
   <div>
     ssssssssssssssssss
     ssssssssssssssssss
   </div>
   <div>
     ssssssssssssssssss
     ssssssssssssssssss
   </div>
   <div>
     ssssssssssssssssss
     ssssssssssssssssss
   </div>
    </CardComponent >
  );
}




const useStyles = makeStyles(theme => ({
  name: {
    paddingLeft: 3
  },
  media: {
    height: 0,
    paddingTop: '56.25%', // 16:9
  },
  avatar: {
    backgroundColor: red[500],
    width: 28,
    height: 28,
  },
  filePic: {
    color: red[500],
  },
}));