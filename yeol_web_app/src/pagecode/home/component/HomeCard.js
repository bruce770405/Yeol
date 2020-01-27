import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import { red } from '@material-ui/core/colors';
import WhatshotOutlinedIcon from '@material-ui/icons/WhatshotOutlined';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import { CardComponent } from '../../../component/CardComponent';

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
export const HomeCard = (props) => {

  const classes = useStyles();
  
  // const handleLikeClick = () => {
  //   console.log('click article...')
  // };

  const { userName = '!!!我誰!!', title = '文章標題 吃吃吃吃吃喝喝喝喝喝', createTime = '2020/01/11 10:53', watchCount = 9999 } = props;


  return (
    <CardComponent>

      <Grid container direction="row" justify="flex-start" alignItems="center" >
        <Avatar className={classes.avatar}>B</Avatar>
        <span className={classes.name}>{userName}</span>&emsp;
        <span className="MuiTypography-root MuiCardHeader-subheader MuiTypography-body2 MuiTypography-colorTextSecondary">{createTime}</span>
      </Grid>

      <Grid container
        direction="row"
        justify="center"
        alignItems="stretch">
        <Grid item xs={3}>
          <div className="MuiTypography-displayBlock">
            讚:
           <span className="MuiTypography-root MuiCardHeader-subheader MuiTypography-body2 MuiTypography-colorTextSecondary">99999</span>
          </div>
          <div className="MuiTypography-displayBlock">
            噓:
           <span className="MuiTypography-root MuiCardHeader-subheader MuiTypography-body2 MuiTypography-colorTextSecondary">999</span>
          </div>
        </Grid>

        <Grid item xs={7}>
          <Typography variant="h6" component="h3">{title}</Typography>
        </Grid>

        <Grid item xs>
          <Typography>觀看數:{watchCount}
            {watchCount > 999 ? <WhatshotOutlinedIcon className={classes.filePic} /> : null}
          </Typography>

        </Grid>

      </Grid>

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