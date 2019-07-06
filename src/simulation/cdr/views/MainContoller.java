package simulation.cdr.views;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import simulation.cdr.business.SimulerAnnee;
import simulation.cdr.models.Adherent;
import simulation.cdr.models.Caisse;
import simulation.cdr.models.Echantillon;
import simulation.cdr.models.LineEchantillon;

public class MainContoller{
	
	/*
	 Attributs d'une simulation simple
	 * */
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField ageRetraite;
	@FXML
	private TextField iXTextField;
	@FXML
	private TextField iYTextField;
	@FXML
	private TextField iZTextField;
	
	@FXML
	private LineChart<String, Number> lineChart;
	
	@FXML 
	private BarChart<String, Number> barChartRet;  
	
	@FXML 
	private BarChart<String, Number> barChartEmp;  

	@FXML private CheckBox ageVarCheckBox;
	
	
	/*
	 Attributs d'un echantillon
	 * */
	@FXML
	private DatePicker datePickerEch;
	@FXML
	private TextField ageRetraiteEch;
	@FXML
	private TextField iXTextFieldEch;
	@FXML
	private TextField iYTextFieldEch;
	@FXML
	private TextField iZTextFieldEch;
	@FXML
	private TextField nbreEchantillon;
	@FXML
	private CheckBox ageVarCheckBoxEch;
	
	/*
	 Attributs affichage tableaux résultat
	 * */
	@FXML private TableView<LineEchantillon> table;
	@FXML private TableColumn<LineEchantillon, Integer> numEch;
	@FXML private TableColumn<LineEchantillon, Integer> annee;
	@FXML private TableColumn<LineEchantillon, Integer> entrees;
	@FXML private TableColumn<LineEchantillon, Integer> sorties;
	@FXML private TableColumn<LineEchantillon, Integer> nbEmp;
	@FXML private TableColumn<LineEchantillon, Integer> nvEmp;
	@FXML private TableColumn<LineEchantillon, Integer> nbRet;
	@FXML private TableColumn<LineEchantillon, Integer> nvRet;
	
	public ObservableList<LineEchantillon> list = FXCollections.observableArrayList();
	
	/*
	 Affichage Nombre moyen ret et emp
	 * */
	@FXML PieChart pieChartEmp;
	@FXML PieChart pieChartRet;
	ObservableList<Data> listPieChartEmp = FXCollections.observableArrayList();
	ObservableList<Data> listPieChartRet = FXCollections.observableArrayList();
	
	@FXML private Label labelEmp;
	@FXML private Label labelRet;
	
	/*
	 Affichage entrees / sorties / reserve moyen
	 * */
	@FXML private AreaChart<?, ?> areaChart;
	
	/*
	 Affichage intervalles de confiance
	 * */
	@FXML private Label intconfres;
	@FXML private Label intconfemp;
	@FXML private Label intconfret;
	
	
	SimulerAnnee sm = new SimulerAnnee();
	
	private Caisse caisse = new Caisse();
	private Echantillon echantillon = new Echantillon();
	
	private List<Adherent> emps = new ArrayList<>();
	private List<Adherent> rets = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void simulerAnnee(ActionEvent event) {
		
		if(ageVarCheckBox.isSelected()) {
			
			System.out.println("Séléctionné");
			
			/*
			 Affichage graphique des entrees /sorties
			 * */
			caisse=sm.simulerAgeVar(Integer.parseInt(iXTextField.getText()), Integer.parseInt(iYTextField.getText()), Integer.parseInt(iZTextField.getText()), Integer.parseInt(ageRetraite.getText()), datePicker.getValue().getYear());
			XYChart.Series<String, Number> entrees = new XYChart.Series<String, Number>();
			entrees.setName("Entrées");
			for(int i=0;i<caisse.getAnnees().size();i++) {
				entrees.getData().add(new XYChart.Data<String, Number>(Integer.toString(caisse.getAnnees().get(i).getAnnee()),caisse.getAnnees().get(i).getLesMois().get(1).getEntree()*12));
			}
	
			XYChart.Series<String, Number> sorties = new XYChart.Series<String, Number>();
			sorties.setName("Sorties");
			for(int i=0;i<caisse.getAnnees().size();i++) {
				sorties.getData().add(new XYChart.Data<String, Number>(Integer.toString(caisse.getAnnees().get(i).getAnnee()),caisse.getAnnees().get(i).getLesMois().get(1).getSortie()*12));
			}
			
			XYChart.Series<String, Number> reserve = new XYChart.Series<String, Number>();
			reserve.setName("Reserves");
			for(int i=0;i<caisse.getAnnees().size();i++) {
				reserve.getData().add(new XYChart.Data<String, Number>(Integer.toString(caisse.getAnnees().get(i).getAnnee()),caisse.getAnnees().get(i).getLesMois().get(1).getEntree()*12-caisse.getAnnees().get(i).getLesMois().get(1).getSortie()*12));
			}
			
			lineChart.getData().addAll(entrees,sorties, reserve);
			
			/*
			 Affichage graphique des nouveaux retraités et recrutés par an
			 * */
			XYChart.Series<String, Number> setNRet = new XYChart.Series<String, Number>();
			setNRet.setName("Nouveaux retraités");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setNRet.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNouvRetraites()));
			}
			XYChart.Series<String, Number> setNEmp = new XYChart.Series<String, Number>();
			setNEmp.setName("Nouveaux employés");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setNEmp.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNouvRecrutes()));
			}
			barChartRet.getData().addAll(setNRet, setNEmp);
			
			/*
			 Affichage graphique total emp et ret
			 * */
			
			XYChart.Series<String, Number> setEmp2 = new XYChart.Series<String, Number>();
			setEmp2.setName("Total employés");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setEmp2.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNbreEmployes()));
			}
			XYChart.Series<String, Number> setRet2 = new XYChart.Series<String, Number>();
			setRet2.setName("Total retraités");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setRet2.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNbreRetraites()));
			}
			barChartEmp.getData().addAll(setEmp2, setRet2);
			
		}
		else {
			
			/*
			 Affichage graphique des entrees /sorties
			 * */
			caisse=sm.simuler(Integer.parseInt(iXTextField.getText()), Integer.parseInt(iYTextField.getText()), Integer.parseInt(iZTextField.getText()), Integer.parseInt(ageRetraite.getText()), datePicker.getValue().getYear());
			XYChart.Series<String, Number> entrees = new XYChart.Series<String, Number>();
			entrees.setName("Entrées");
			for(int i=0;i<caisse.getAnnees().size();i++) {
				entrees.getData().add(new XYChart.Data<String, Number>(Integer.toString(caisse.getAnnees().get(i).getAnnee()),caisse.getAnnees().get(i).getLesMois().get(1).getEntree()*12));
			}
	
			XYChart.Series<String, Number> sorties = new XYChart.Series<String, Number>();
			sorties.setName("Sorties");
			for(int i=0;i<caisse.getAnnees().size();i++) {
				sorties.getData().add(new XYChart.Data<String, Number>(Integer.toString(caisse.getAnnees().get(i).getAnnee()),caisse.getAnnees().get(i).getLesMois().get(1).getSortie()*12));
			}
			
			XYChart.Series<String, Number> reserve = new XYChart.Series<String, Number>();
			reserve.setName("Reserves");
			for(int i=0;i<caisse.getAnnees().size();i++) {
				reserve.getData().add(new XYChart.Data<String, Number>(Integer.toString(caisse.getAnnees().get(i).getAnnee()),caisse.getAnnees().get(i).getLesMois().get(1).getEntree()*12-caisse.getAnnees().get(i).getLesMois().get(1).getSortie()*12));
			}
			
			lineChart.getData().addAll(entrees,sorties, reserve);
			
			
			/*
			 Affichage graphique des nouveaux retraités et recrutés par an
			 * */
			XYChart.Series<String, Number> setNRet = new XYChart.Series<String, Number>();
			setNRet.setName("Nouveaux retraités");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setNRet.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNouvRetraites()));
			}
			XYChart.Series<String, Number> setNEmp = new XYChart.Series<String, Number>();
			setNEmp.setName("Nouveaux employés");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setNEmp.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNouvRecrutes()));
			}
			barChartRet.getData().addAll(setNRet, setNEmp);
			
			/*
			 Affichage graphique total emp et ret
			 * */
			
			XYChart.Series<String, Number> setEmp2 = new XYChart.Series<String, Number>();
			setEmp2.setName("Total employés");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setEmp2.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNbreEmployes()));
			}
			XYChart.Series<String, Number> setRet2 = new XYChart.Series<String, Number>();
			setRet2.setName("Total retraités");
			for(int i=0,j=2017;i<caisse.getAnnees().size();i++,j++) {
				setRet2.getData().add(new XYChart.Data<String, Number>(Integer.toString(j),caisse.getAnnees().get(i).getNbreRetraites()));
			}
			barChartEmp.getData().addAll(setEmp2, setRet2);
		}

		
	}
	
	public void simulerEchantillon(ActionEvent event) {
		
		System.out.println("Selectionné");
		
		if(ageVarCheckBoxEch.isSelected()) {
			echantillon=sm.simulerEchantillonAgeVar(Integer.parseInt(iXTextFieldEch.getText()), Integer.parseInt(iYTextFieldEch.getText()), Integer.parseInt(iZTextFieldEch.getText()), Integer.parseInt(ageRetraiteEch.getText()), datePickerEch.getValue().getYear(),Integer.parseInt(nbreEchantillon.getText()));
			for(int i=0;i<echantillon.getLesCaisses().size();i++) {
				for(int j=0;j<echantillon.getLesCaisses().get(i).getAnnees().size();j++) {
					list.add(new LineEchantillon(
							i,
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getAnnee() , 
							new Integer((int)echantillon.getLesCaisses().get(i).getAnnees().get(j).getLesMois().get(1).getEntree())*12,
							new Integer((int)echantillon.getLesCaisses().get(i).getAnnees().get(j).getLesMois().get(1).getSortie())*12, 
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNbreEmployes(),
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNouvRecrutes(), 
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNbreRetraites(),
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNouvRetraites())
							);
				}
			}
			
			annee.setCellValueFactory(new PropertyValueFactory<LineEchantillon, Integer>("annee"));
		    entrees.setCellValueFactory(new PropertyValueFactory<LineEchantillon, Integer>("entrees"));
		    sorties.setCellValueFactory(new PropertyValueFactory<LineEchantillon, Integer>("sorties"));

			table.setItems(list);
			
			int somme=0;
			double tmp=0;
			List<Integer> listSomme=new ArrayList<>();

				for(int j=0;j<echantillon.getLesCaisses().size();j++) {
					for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
						for(int k=0;k<echantillon.getLesCaisses().size();k++) {
							somme+=echantillon.getLesCaisses().get(k).getAnnees().get(i).getNbreEmployes();
						}
						listSomme.add(somme);
						listPieChartEmp.add(new Data(Integer.toString(2017+i), somme/echantillon.getLesCaisses().size()));
						somme=0;
					}
					break;
				}
				
				double moyenne=0;
				for(int i=0;i<listSomme.size();i++) {
					moyenne+=listSomme.get(i);
				}
				moyenne=moyenne/listSomme.size();
				
				/*
				 Calcul ecart type nbreEmployés
				 * 
				 * */
				for(int i=0;i<listSomme.size();i++) {
					tmp=tmp+Math.pow((listSomme.get(i))-(moyenne), 2);
				}
				
				double ecartType=0;
				ecartType=Math.sqrt(tmp/listSomme.size());
				
				double conf=ecartType/Math.sqrt(listSomme.size());
				
				intconfemp.setText(Math.floor((moyenne/(echantillon.getLesCaisses().size()))*100)/100  + " + ou - " + Math.floor(conf/(echantillon.getLesCaisses().size()/2)*100)/100);
				
				
			somme=0;
			List<Integer> listSommeNbreRet = new ArrayList<>();
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=echantillon.getLesCaisses().get(k).getAnnees().get(i).getNbreRetraites();
					}
					listSommeNbreRet.add(somme);
					listPieChartRet.add(new Data(Integer.toString(2017+i), somme/echantillon.getLesCaisses().size()));
					somme=0;
				}
				break;
			}
			
		    moyenne=0;
			for(int i=0;i<listSommeNbreRet.size();i++) {
				moyenne+=listSommeNbreRet.get(i);
			}
			
			moyenne=moyenne/listSommeNbreRet.size();
			
			/*
			 Calcul ecart type nbreretraités
			 * 
			 * */
			for(int i=0;i<listSommeNbreRet.size();i++) {
				tmp=tmp+Math.pow((listSommeNbreRet.get(i))-(moyenne), 2);
			}
			
			ecartType=0;
			ecartType=Math.sqrt(tmp/listSommeNbreRet.size());
			
			conf=ecartType/Math.sqrt(listSommeNbreRet.size());
			
			intconfret.setText(Math.floor((moyenne/(echantillon.getLesCaisses().size()))*100)/100 + " + ou - " + Math.floor(conf /(echantillon.getLesCaisses().size()/2)* 100) / 100);
			
			
			pieChartEmp.setData(listPieChartEmp);
			pieChartRet.setData(listPieChartRet);
			
			labelEmp.setFont(Font.font("SanSerif", FontWeight.BOLD, 12));
			labelRet.setFont(Font.font("SanSerif", FontWeight.BOLD, 12));
			
			pieChartEmp.getData().stream().forEach(
					data ->{
						data.getNode().addEventHandler(javafx.scene.input.MouseEvent.ANY, e->{
							labelEmp.setText(data.getName() + " = " + data.getPieValue());
						});
					}
					);
			pieChartRet.getData().stream().forEach(
					data ->{
						data.getNode().addEventHandler(javafx.scene.input.MouseEvent.ANY, e->{
							labelRet.setText(data.getName() + " = " + data.getPieValue());
						});
					}
					);
			
			
			
			
			XYChart.Series  seriesEntr = new XYChart.Series();
			seriesEntr.setName("Entrée moyenne");
			
			List<Integer> listSommeEnt = new ArrayList<>();
			
			somme=0;
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getEntree())*12;
					}
					listSommeEnt.add(somme);
					seriesEntr.getData().add(new XYChart.Data(Integer.toString(2017+i),somme/echantillon.getLesCaisses().size()));
				}
				break;
			}
			
			moyenne=0;
			for(int i=0;i<listSommeEnt.size();i++) {
				moyenne+=listSommeEnt.get(i);
			}
			
			moyenne=moyenne/listSommeEnt.size();
			
			
			XYChart.Series  seriesSort = new XYChart.Series();
			seriesSort.setName("Sortie moyenne");
			List<Integer> listSommeSot = new ArrayList<>();
			
			somme=0;
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getSortie())*12;
					}
					listSommeSot.add(somme);
					seriesSort.getData().add(new XYChart.Data(Integer.toString(2017+i),somme/echantillon.getLesCaisses().size()));
				}
				break;
			}
			
			double moyenne2=0;
			for(int i=0;i<listSommeSot.size();i++) {
				moyenne2+=listSommeSot.get(i);
			}
			
			moyenne2=moyenne2/listSommeSot.size();
			
			moyenne-=moyenne2;
			
			/*
			 Calcul ecart reserve
			 * 
			 * */
			for(int i=0;i<listSommeSot.size();i++) {
				tmp=tmp+Math.pow((listSommeSot.get(i)-listSommeEnt.get(i))-(moyenne), 2);
			}
			
			ecartType=0;
			ecartType=Math.sqrt(tmp/listSommeEnt.size());
			
			conf=ecartType/Math.sqrt(listSommeEnt.size());
			
			intconfres.setText(Math.floor((moyenne/2)*100)/100  + " + ou - " + (Math.floor(conf*100)/100)/4);
			//Math.floor(x * 100) / 100;
			
			
			XYChart.Series  seriesRes = new XYChart.Series();
			seriesRes.setName("Resèrve moyenne");
			
			somme=0;
			int somme2=0;
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getSortie())*12;
						somme2+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getEntree())*12;
					}
					seriesRes.getData().add(new XYChart.Data(Integer.toString(2017+i),(somme2-somme)/echantillon.getLesCaisses().size()));
				}
				break;
			}
			
			// Intervalle de confiance Reserve
			
			areaChart.getData().add(seriesEntr);
			areaChart.getData().add(seriesSort);
			areaChart.getData().add(seriesRes);
		}
		else {
			echantillon=sm.simulerEchantillon(Integer.parseInt(iXTextFieldEch.getText()), Integer.parseInt(iYTextFieldEch.getText()), Integer.parseInt(iZTextFieldEch.getText()), Integer.parseInt(ageRetraiteEch.getText()), datePickerEch.getValue().getYear(),Integer.parseInt(nbreEchantillon.getText()));
			for(int i=0;i<echantillon.getLesCaisses().size();i++) {
				for(int j=0;j<echantillon.getLesCaisses().get(i).getAnnees().size();j++) {
					list.add(new LineEchantillon(
							i,
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getAnnee() , 
							new Integer((int)echantillon.getLesCaisses().get(i).getAnnees().get(j).getLesMois().get(1).getEntree())*12,
							new Integer((int)echantillon.getLesCaisses().get(i).getAnnees().get(j).getLesMois().get(1).getSortie())*12, 
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNbreEmployes(),
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNouvRecrutes(), 
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNbreRetraites(),
							echantillon.getLesCaisses().get(i).getAnnees().get(j).getNouvRetraites())
							);
				}
			}
			
			annee.setCellValueFactory(new PropertyValueFactory<LineEchantillon, Integer>("annee"));
		    entrees.setCellValueFactory(new PropertyValueFactory<LineEchantillon, Integer>("entrees"));
		    sorties.setCellValueFactory(new PropertyValueFactory<LineEchantillon, Integer>("sorties"));

			table.setItems(list);
			
			int somme=0;
			double tmp=0;
			List<Integer> listSomme=new ArrayList<>();

				for(int j=0;j<echantillon.getLesCaisses().size();j++) {
					for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
						for(int k=0;k<echantillon.getLesCaisses().size();k++) {
							somme+=echantillon.getLesCaisses().get(k).getAnnees().get(i).getNbreEmployes();
						}
						listSomme.add(somme);
						listPieChartEmp.add(new Data(Integer.toString(2017+i), somme/echantillon.getLesCaisses().size()));
						somme=0;
					}
					break;
				}
				
				double moyenne=0;
				for(int i=0;i<listSomme.size();i++) {
					moyenne+=listSomme.get(i);
				}
				moyenne=moyenne/listSomme.size();
				
				/*
				 Calcul ecart type nbreEmployés
				 * 
				 * */
				for(int i=0;i<listSomme.size();i++) {
					tmp=tmp+Math.pow((listSomme.get(i))-(moyenne), 2);
				}
				
				double ecartType=0;
				ecartType=Math.sqrt(tmp/listSomme.size());
				
				double conf=ecartType/Math.sqrt(listSomme.size());
				
				intconfemp.setText(Math.floor((moyenne/(echantillon.getLesCaisses().size()))*100)/100  + " + ou - " + Math.floor(conf/(echantillon.getLesCaisses().size()/2)*100)/100);
				
				
			somme=0;
			List<Integer> listSommeNbreRet = new ArrayList<>();
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=echantillon.getLesCaisses().get(k).getAnnees().get(i).getNbreRetraites();
					}
					listSommeNbreRet.add(somme);
					listPieChartRet.add(new Data(Integer.toString(2017+i), somme/echantillon.getLesCaisses().size()));
					somme=0;
				}
				break;
			}
			
		    moyenne=0;
			for(int i=0;i<listSommeNbreRet.size();i++) {
				moyenne+=listSommeNbreRet.get(i);
			}
			
			moyenne=moyenne/listSommeNbreRet.size();
			
			/*
			 Calcul ecart type nbreretraités
			 * 
			 * */
			for(int i=0;i<listSommeNbreRet.size();i++) {
				tmp=tmp+Math.pow((listSommeNbreRet.get(i))-(moyenne), 2);
			}
			
			ecartType=0;
			ecartType=Math.sqrt(tmp/listSommeNbreRet.size());
			
			conf=ecartType/Math.sqrt(listSommeNbreRet.size());
			
			intconfret.setText(Math.floor((moyenne/(echantillon.getLesCaisses().size()))*100)/100 + " + ou - " + Math.floor(conf /(echantillon.getLesCaisses().size()/2)* 100) / 100);
			
			
			pieChartEmp.setData(listPieChartEmp);
			pieChartRet.setData(listPieChartRet);
			
			labelEmp.setFont(Font.font("SanSerif", FontWeight.BOLD, 12));
			labelRet.setFont(Font.font("SanSerif", FontWeight.BOLD, 12));
			
			pieChartEmp.getData().stream().forEach(
					data ->{
						data.getNode().addEventHandler(javafx.scene.input.MouseEvent.ANY, e->{
							labelEmp.setText(data.getName() + " = " + data.getPieValue());
						});
					}
					);
			pieChartRet.getData().stream().forEach(
					data ->{
						data.getNode().addEventHandler(javafx.scene.input.MouseEvent.ANY, e->{
							labelRet.setText(data.getName() + " = " + data.getPieValue());
						});
					}
					);
			
			
			
			
			XYChart.Series  seriesEntr = new XYChart.Series();
			seriesEntr.setName("Entrée moyenne");
			
			List<Integer> listSommeEnt = new ArrayList<>();
			
			somme=0;
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getEntree())*12;
					}
					listSommeEnt.add(somme);
					seriesEntr.getData().add(new XYChart.Data(Integer.toString(2017+i),somme/echantillon.getLesCaisses().size()));
				}
				break;
			}
			
			moyenne=0;
			for(int i=0;i<listSommeEnt.size();i++) {
				moyenne+=listSommeEnt.get(i);
			}
			
			moyenne=moyenne/listSommeEnt.size();
			
			
			XYChart.Series  seriesSort = new XYChart.Series();
			seriesSort.setName("Sortie moyenne");
			List<Integer> listSommeSot = new ArrayList<>();
			
			somme=0;
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getSortie())*12;
					}
					listSommeSot.add(somme);
					seriesSort.getData().add(new XYChart.Data(Integer.toString(2017+i),somme/echantillon.getLesCaisses().size()));
				}
				break;
			}
			
			double moyenne2=0;
			for(int i=0;i<listSommeSot.size();i++) {
				moyenne2+=listSommeSot.get(i);
			}
			
			moyenne2=moyenne2/listSommeSot.size();
			
			moyenne-=moyenne2;
			
			/*
			 Calcul ecart reserve
			 * 
			 * */
			for(int i=0;i<listSommeSot.size();i++) {
				tmp=tmp+Math.pow((listSommeSot.get(i)-listSommeEnt.get(i))-(moyenne), 2);
			}
			
			ecartType=0;
			ecartType=Math.sqrt(tmp/listSommeEnt.size());
			
			conf=ecartType/Math.sqrt(listSommeEnt.size());
			
			intconfres.setText(Math.floor((moyenne/2)*100)/100  + " + ou - " + (Math.floor(conf*100)/100)/4);
			//Math.floor(x * 100) / 100;
			
			
			XYChart.Series  seriesRes = new XYChart.Series();
			seriesRes.setName("Resèrve moyenne");
			
			somme=0;
			int somme2=0;
			for(int j=0;j<echantillon.getLesCaisses().size();j++) {
				for(int i=0;i<echantillon.getLesCaisses().get(j).getAnnees().size();i++) {
					for(int k=0;k<echantillon.getLesCaisses().size();k++) {
						somme+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getSortie())*12;
						somme2+=(echantillon.getLesCaisses().get(k).getAnnees().get(i).getLesMois().get(1).getEntree())*12;
					}
					seriesRes.getData().add(new XYChart.Data(Integer.toString(2017+i),(somme2-somme)/echantillon.getLesCaisses().size()));
				}
				break;
			}
			
			// Intervalle de confiance Reserve
			
			areaChart.getData().add(seriesEntr);
			areaChart.getData().add(seriesSort);
			areaChart.getData().add(seriesRes);
		}
		
	}

}
