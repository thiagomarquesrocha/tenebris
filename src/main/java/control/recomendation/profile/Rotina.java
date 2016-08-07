package control.recomendation.profile;

import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Rotina extends TimerTask {
	
	String indexDir = "Index";
	Indexer indexer;
	private final static long fONCE_PER_DAY = 1000 * 60 * 60 * 24;
	private final static int fONE_DAY = 0;
	private final static int fFOUR_AM = 15;
	private final static int fZERO_MINUTES = 43;
	private static Rotina instancia = new Rotina();
	private static boolean cod = true;
	
	private Rotina(){
		
	}
	
	public static void Executar(){
		if(cod){
			instancia.ExecutarRotina();
			cod = false;
		}
	}
	

	public void ExecutarRotina() {
		TimerTask fetchMail = new Rotina();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(fetchMail, getTomorrowMorning4am(), fONCE_PER_DAY);
	}

	@Override
	public void run(){
		try{
			createIndex();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Os documentos foram indexados!");
	}
	
	private void createIndex() throws SQLException, IOException {
		indexer = new Indexer(indexDir);
		indexer.createIndex();
	}
	
	private static Date getTomorrowMorning4am() {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DATE, fONE_DAY);
		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE), fFOUR_AM, fZERO_MINUTES);
		return result.getTime();
	}
}