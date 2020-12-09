import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import { red } from '@material-ui/core/colors';
import List from '@material-ui/core/List';
import ArticleCard from './ArticleCard';

/**
 * 畫面卡片式元件
 * @author BruceHsu
 * @version
 * @since
 * @param userName 
 * @param title  
 * @param createTime
 * @param watchCount
 */
export const ArticleList = (props) => {

  const classes = useStyles();

  return (
    <List className={classes.root}>
      {
        props.data ?
          Object.values(props.data).map(item =>
            <React.Fragment>
              <ArticleCard item={item}></ArticleCard>
              <div style={{ marginTop: 10, marginBottom: 10 }} />
            </React.Fragment>) : null
      }
    </List>
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