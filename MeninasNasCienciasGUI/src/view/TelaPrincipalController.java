package view;

import java.io.Console;
import java.net.URL;
import java.util.ResourceBundle;

import Domain.Equipe;
import Persistence.EquipeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TelaPrincipalController implements Initializable {

	 @FXML
	    private AnchorPane apnEquipe, apnListaEquipe, apnLogin, apnMaterial, apnParticipante;
	 
	 	@FXML
	 	private Label lblMensagem;

	    @FXML
	    private Button btnAddEquipe, btnSair, btnVoltar, btnAlterar, btnBuscar, btnEntrar, btnExcluir, btnListar;

	    @FXML
	    private TableView<Equipe> tbvEquipe;

	    @FXML
	    private TableColumn<Equipe, String> tbcCidade, tbcEscola, tbcNivelEscolar;

	    @FXML
	    private TableColumn<Equipe, Integer> tbcId;
	    
	    @FXML
	    private TextField txtCidade, txtEscola, txtID, txtIdEditar, txtLogin, txtNivelEscolar;

	    @FXML
	    private PasswordField txtSenha;
	    
	private EquipeDAO eqdao = new EquipeDAO();
	
	int op; //0 - Inclusão, 1 - Limpar tela, 2 - Alterar
    private ObservableList<Equipe> equipes = FXCollections.observableArrayList();
    
	    @Override
		public void initialize(URL url, ResourceBundle rb) {
	    	apnLogin.setVisible(true);
	    	apnListaEquipe.setVisible(false);
			apnEquipe.setVisible(false);
			apnMaterial.setVisible(false);
		}
		
		@FXML
		private void handlerbtnEntrar() {
			String login = "abc";
	    	String senha = "123";
	    	if(txtLogin.getText().equals(login) && txtSenha.getText().equals(senha)) {
	       		apnLogin.setVisible(false);
	       		apnEquipe.setVisible(true);
		        op = 1;
		        configurarTela();
	    	} else {
		       	txtLogin.setText("");
		       	txtSenha.setText("");
	            txtLogin.requestFocus();
	    	}
		}
		
		
		@FXML
		private void handlerbtnAddEquipe() {
			op =0;
			configurarTela();
			Equipe novaEquipe = new Equipe();
			novaEquipe.setCidade(txtCidade.getText());
			novaEquipe.setEscola(txtEscola.getText());
			novaEquipe.setNivelEscolar(txtNivelEscolar.getText());
			eqdao.Adicionar(novaEquipe);
			op=1;
			configurarTela();
			lblMensagem.setText("A Equipe foi salva!");
		}
		

		@FXML
	    private void handlerbtnBuscarEquipe() {
			Equipe eEditar = eqdao.consultarPorId(Integer.parseInt(txtID.getText()) );
			if(eEditar!=null) {
				txtCidade.setText(eEditar.getCidade());
				txtEscola.setText(eEditar.getEscola());
				txtNivelEscolar.setText(eEditar.getNivelEscolar());
				txtIdEditar.setText(Integer.toString(eEditar.getId()));
				op =2;
				configurarTela();
			}else {
				op = 1;
				configurarTela();
				lblMensagem.setText("Equipe NÃO encontrada para esse ID!");
			}
			
			
		}
		
		@FXML
		private void handlerbtnAlterar() {
			Equipe eEditar = eqdao.consultarPorId(Integer.parseInt(txtIdEditar.getText()) );
			eEditar.setCidade(txtCidade.getText());
			eEditar.setEscola(txtEscola.getText());
			eEditar.setNivelEscolar(txtNivelEscolar.getText());
			eqdao.Alterar(eEditar);
			op =1;
			configurarTela();
			lblMensagem.setText("Equipe alterada com sucesso");
			
		}
		
		@FXML
		private void handlerbtnExcluir() {
			Equipe eEditar = eqdao.consultarPorId(Integer.parseInt(txtIdEditar.getText()) );
			
			eqdao.Excluir(eEditar);
			op =1;
			configurarTela();
			lblMensagem.setText("Equipe Excluída com sucesso");
			
		}
		
		@FXML
	    private void handleBtnSairAction() {
	    	if(btnSair.getText().equals("Sair")){
		        apnLogin.setVisible(true);
		       	apnEquipe.setVisible(false);
		       	txtLogin.setText("");
		       	txtSenha.setText("");
	    	} else {
	        	op = 0;
	        	configurarTela();
	    	}
	    }
		
		@FXML
	    private void handleBtnVoltar() {
	    	if(btnVoltar.getText().equals("Voltar")){
		        apnLogin.setVisible(false);
		       	apnEquipe.setVisible(true);
		       	apnListaEquipe.setVisible(false);
	    	} else {
	        	op = 1;
	        	configurarTela();
	    	}
	    }
		
		@FXML
	    private void handleBtListar() {
	       	apnEquipe.setVisible(false);
	    	apnListaEquipe.setVisible(true);
		    tbcId.setCellValueFactory(new PropertyValueFactory<>("id")); //nome do atributo
		    tbcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade")); 
		    tbcEscola.setCellValueFactory(new PropertyValueFactory<>("escola"));
		    tbcNivelEscolar.setCellValueFactory(new PropertyValueFactory<>("nivelEscolar"));
		    //populando a TableView - inserindo a lista de contatos
		    //pegando do bd: adiciona o retorno da DAO na ObsarvableList
		    equipes.addAll(eqdao.listar());
		    tbvEquipe.setItems(equipes);
		    System.out.println(equipes.size());
	    }
		
		private void configurarTela() {
	    	switch(op) {
		    	case 0: // inclusão
		    		txtCidade.setEditable(false); 
		    		txtNivelEscolar.setEditable(false); 
		            txtEscola.setEditable(false);
		            btnAddEquipe.setDisable(true);
		            
		            break;
		    		case 1: // limpar tela
		    			txtCidade.setEditable(true); 
			    		txtNivelEscolar.setEditable(true); 
			            txtEscola.setEditable(true);
			            btnAddEquipe.setDisable(false);
			            txtCidade.setText("");
			            txtEscola.setText("");
			            txtNivelEscolar.setText("");
			            lblMensagem.setText("");
			            txtIdEditar.setText("");
			            
		            break;
		    	case 2: // alteração ou exclusão
		            btnAddEquipe.setDisable(true);
		            
		            break;   
	    	}
	    }
	
}
