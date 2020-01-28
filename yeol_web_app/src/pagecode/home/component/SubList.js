import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
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
    <CardComponent className={classes.card}>
      <CardActionArea>
        <CardMedia
          component="img"
          alt="Contemplative Reptile"
          height="140"
          image="test.jpg"
          title="Contemplative Reptile"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            廣告
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            廣告內容
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary">
          臉書推廣
        </Button>
      </CardActions>
    </CardComponent >
  );
}




const useStyles = makeStyles(theme => ({
  card: {
    maxWidth: 100,
  },
}));