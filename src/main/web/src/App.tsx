import React, { useState } from 'react';
import { makeStyles } from "@material-ui/core/styles";
import AssignmentTurnedInIcon from '@material-ui/icons/AssignmentTurnedIn';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import Checkbox from '@material-ui/core/Checkbox';
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Grid from "@material-ui/core/Grid";
import IconButton from '@material-ui/core/IconButton';
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";

// Estilização
const useStyles = makeStyles(theme => ({
  head: {
    marginTop: theme.spacing(7),
    display: "flex",
    flexDirection: "column",
    width: "100%",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.primary.main
  },
  form: {
    width: "100%",
    marginTop: theme.spacing(2)
  },
  submit: {
    margin: theme.spacing(1, 0, 2),
    width: "15%"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "80%"
  },
  title: {
    marginTop: theme.spacing(1),
  }
}));

export default function App() {

  const classes = useStyles();

  // Conjunto de Tasks, proveniente do banco de dados e atualizado conforme uso
  const [data, setData] = useState<any[]>([]);

  // Valores dos campos do formulário
  const [title, setTitle] = React.useState("");
  const [description, setDescription] = React.useState("");
  const [status, setStatus] = React.useState(false);
  const [creation, setCreation] = React.useState();
  const [lastEdition, setLastEdition] = React.useState();
  const [removal, setRemoval] = React.useState();
  const [conclusion, setConclusion] = React.useState();

  // Inicialização da lista, atualizando as Tasks
  asyncList();

  // Inicialização, com GET para listar as Tasks
  async function asyncList() {
    let response = await fetch("/tasklist");
    let body = await response.json();
    setData(body);
  }

  // Salvar e atualizar lista
  async function asyncSave(toSave: any) {
    await fetch('/savetask', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(toSave)
    }).then(() => asyncList());
  }

  // Deleta e atualiza lista
  async function asyncDelete(id: number) {
    await fetch('/deletetask', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(id)
    }).then(() => asyncList());
  }

  // Handler do botão Save
  const handleSubmit = () => {
    const toSave = { title, description, status, creation, lastEdition, removal, conclusion };

    asyncSave(toSave);

    setTitle("");
  }

  // Handlers de eventos de atualização do formulário 
  const handleTitleChange = (event: { target: { value: React.SetStateAction<string>; }; }) => setTitle(event.target.value);

  // Handler da checkbox que indica se Task está ou não completa
  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>, index: number) => {
    const aux: any[] = [...data];

    aux[index].status = event.target.checked;

    asyncSave(aux[index]);
  };

  const handleDelete = (event: React.MouseEvent<SVGSVGElement, MouseEvent>, id: number) => {
    asyncDelete(id);
  }

  // Componentes da interface
  return (
    <Container component="main" maxWidth="sm">
      <CssBaseline />

      <div>
        <div className={classes.head}>
          <Avatar className={classes.avatar} >
            <AssignmentTurnedInIcon />
          </Avatar>
        </div>
        <Typography component="h1" variant="h5" align="center">
          My Beautiful Task List!
        </Typography>

        {data.map((item, index) => (
          <Grid container spacing={1}>
            <Grid item xs={1} >
              <Checkbox checked={item.status} onChange={event => handleStatusChange(event, index)} />
            </Grid>
            <Grid item xs={10} className={classes.title}>
              <Typography variant="body1" align="left" noWrap>
                {item.title}
              </Typography>
            </Grid>
            <Grid item xs={1} >
              <IconButton>
                <DeleteOutline onClick={event => handleDelete(event, item.id)} />
              </IconButton>
            </Grid>
          </Grid>
        ))}

        <form className={classes.form} onSubmit={handleSubmit} noValidate>
          <Grid>
            <TextField name="title" className={classes.textField} label="Title" value={title} onChange={handleTitleChange} inputProps={{ maxLength: 250 }} />

            <Button variant="contained" color="primary" className={classes.submit} onClick={handleSubmit}>
              Save
            </Button>
          </Grid>
        </form>

      </div>

    </Container>
  );
}