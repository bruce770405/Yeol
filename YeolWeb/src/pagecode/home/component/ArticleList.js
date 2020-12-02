import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import { red } from '@material-ui/core/colors';
import Typography from '@material-ui/core/Typography';
import { CardComponent } from '../../../component/CardComponent';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import Divider from '@material-ui/core/Divider';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Grid from '@material-ui/core/Grid';
import ThumbDownOutlinedIcon from '@material-ui/icons/ThumbDownOutlined';
import InsertEmoticonOutlinedIcon from '@material-ui/icons/InsertEmoticonOutlined';

/**
 * 畫面卡片式元件
 * TODO
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

    <CardComponent>
      <List className={classes.root}>
        {
          props.data ?
            Object.values(props.data).map(item =>
              <React.Fragment>
                <ListItem alignItems="flex-start">
                  <ListItemAvatar>
                    <Avatar className={classes.orange}>B</Avatar>
                  </ListItemAvatar>
                  <ListItemText primary={item.title}
                    secondary={
                      <React.Fragment>
                        <Typography
                          component="span"
                          variant="body2"
                          className={classes.inline}
                          color="textPrimary"
                        >
                          Ali Connors :
                        </Typography>
                        {item.content}
                      </React.Fragment>
                    }
                  />
                  <Grid container direction="column"
                    justify="flex-end"
                    alignItems="flex-end" xs={2}>
                    <Grid item>
                      <InsertEmoticonOutlinedIcon fontSize="small" color="primary" />{item.up}
                      &nbsp;&nbsp;
                      <ThumbDownOutlinedIcon fontSize="small" />{item.down}
                    </Grid>

                    <Typography variant="overline" display="block" gutterBottom>2020/01/02</Typography>
                  </Grid>

                </ListItem>
                <Divider variant="inset" component="li" />
              </React.Fragment>) : null
        }
      </List>
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