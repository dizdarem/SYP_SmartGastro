package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

import bll.BestellungProdukt;
import bll.Produkt;
import bll.BestellungGruppiert;
import bll.Posten;
import bll.ProduktTyp;
import bll.TischBG;

public class Database {
	private static Database instance = null;
	private static Connection con = null;

	private Database() {

	}

	public static Database newInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private static void openConnection() throws Exception {
		if (con == null || con.isClosed()) {
			/*
			 * access using MySQL Class.forName("com.mysql.jdbc.Driver"); Connection con =
			 * DriverManager.getConnection(
			 * "jdbc:mysql://localhost:3306/5A10?useLegacyDatetimeCode=false&serverTimezone=UTC",
			 * "root", "admin");
			 */

			/* internal access */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.6.111:1521:ora11g", "d5a12", "d5a");

			// external access
			/*
			 * Class.forName("oracle.jdbc.driver.OracleDriver"); con =
			 * DriverManager.getConnection( "jdbc:oracle:thin:@212.152.179.117:1521:ora11g",
			 * "d5a10", "d5a");
			 */

		}
	}

	public ArrayList<Produkt> getProdukte() {
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		try {
			openConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from produkt");
			while (rs.next())
				produkte.add(new Produkt(rs.getInt(1), rs.getString(2), rs.getDouble(3), ProduktTyp.valueOf(rs.getString(4))));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return produkte;
	}
	
	public ArrayList<Produkt> getProdukteByTyp(String typ) {
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		try {
			openConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from produkt WHERE typ LIKE '" + typ + "'");
			while (rs.next())
				produkte.add(new Produkt(rs.getInt(1), rs.getString(2), rs.getDouble(3), ProduktTyp.valueOf(rs.getString(4))));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return produkte;
	}

	public ArrayList<BestellungProdukt> getBestellungProduktD() {
		ArrayList<BestellungProdukt> bestellungProdukt = new ArrayList<BestellungProdukt>();
		try {
			openConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select idBestellung,idTisch,idPosten,idProdukt,bezeichnung,preis,typ from besteht_aus "
							+ "inner join bestellung on besteht_aus.idBestellung = bestellung.id "
							+ "inner join produkt on besteht_aus.idProdukt = produkt.id "
							+ "where produkt.typ = 'getraenk' " + "order by idPosten");
			while (rs.next())
				bestellungProdukt.add(new BestellungProdukt(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getDouble(6), rs.getString(7)));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return bestellungProdukt;
	}

	public ArrayList<BestellungProdukt> getBestellungProduktG() {
		ArrayList<BestellungProdukt> bestellungProdukt = new ArrayList<BestellungProdukt>();
		try {
			openConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select idBestellung,idTisch,idPosten,idProdukt,bezeichnung,preis,typ from besteht_aus "
							+ "inner join bestellung on besteht_aus.idBestellung = bestellung.id "
							+ "inner join produkt on besteht_aus.idProdukt = produkt.id "
							+ "where produkt.typ = 'gericht' or produkt.typ = 'beilage' " + "order by idPosten");
			while (rs.next())
				bestellungProdukt.add(new BestellungProdukt(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getDouble(6), rs.getString(7)));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return bestellungProdukt;
	}

//---under construction---
	/*
	 * select idBestellung,idTisch,to_char(zeitstempel,'dd.mm.yyyy hh24.mi'),bezeichnung,preis,typ from besteht_aus
inner join bestellung on besteht_aus.idBestellung = bestellung.id
inner join produkt on besteht_aus.idProdukt = produkt.id
where produkt.typ = 'gericht' or produkt.typ = 'beilage' or produkt.typ = 'dessert'
order by idBestellung, idPosten;

select idBestellung,idTisch,to_char(zeitstempel,'dd.mm.yyyy hh24.mi'),bezeichnung,preis,typ from besteht_aus
inner join bestellung on besteht_aus.idBestellung = bestellung.id
inner join produkt on besteht_aus.idProdukt = produkt.id
where produkt.typ = 'getraenk'
order by idBestellung, idPosten;
	 */

	public ArrayList<BestellungGruppiert> getBestellungGruppiertG() {
		ArrayList<BestellungGruppiert> arrBg = new ArrayList<BestellungGruppiert>();
		try {
			openConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select idBestellung,idTisch,zeitstempel,idPosten,idProdukt,bezeichnung,preis,typ from besteht_aus"
					+" inner join bestellung on besteht_aus.idBestellung = bestellung.id"
					+" inner join produkt on besteht_aus.idProdukt = produkt.id"
					+" where produkt.typ = 'gericht' or produkt.typ = 'beilage' or produkt.typ = 'dessert'"
					+" order by idBestellung, idPosten");
			/*ResultSet rs = stmt.executeQuery(
					"select idBestellung,idTisch,idPosten,idProdukt,bezeichnung,preis,typ, zeitstempel from besteht_aus "
							+ "inner join bestellung on besteht_aus.idBestellung = bestellung.id "
							+ "inner join produkt on besteht_aus.idProdukt = produkt.id "
							+ "where produkt.typ = 'gericht' or produkt.typ = 'beilage' or produkt.typ = 'dessert'" + "order by idBestellung");*/
			int lastBestellung = 0;

			BestellungGruppiert bg = new BestellungGruppiert();
			while (rs.next()) {
				int idBestellung = rs.getInt(1);
				if (idBestellung != lastBestellung || lastBestellung == 0) {
					bg = new BestellungGruppiert(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), new ArrayList<Posten>());
					arrBg.add(bg);
				}
				bg.getArrPosten().add(new Posten(rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDouble(7),
						ProduktTyp.valueOf(rs.getString(8))));

				lastBestellung = idBestellung;
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrBg;
	}

	public ArrayList<BestellungGruppiert> getBestellungGruppiertD() {
		ArrayList<BestellungGruppiert> arrBg = new ArrayList<BestellungGruppiert>();
		try {
			openConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select idBestellung,idTisch,zeitstempel,idPosten,idProdukt,bezeichnung,preis,typ from besteht_aus"
					+ " inner join bestellung on besteht_aus.idBestellung = bestellung.id"
					+ " inner join produkt on besteht_aus.idProdukt = produkt.id"
					+ " where produkt.typ = 'getraenk'"
					+ " order by idBestellung, idPosten");
			int lastBestellung = 0;

			BestellungGruppiert bg = new BestellungGruppiert();
			while (rs.next()) {
				int idBestellung = rs.getInt(1);
				if (idBestellung != lastBestellung || lastBestellung == 0) {
					bg = new BestellungGruppiert(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3),
							new ArrayList<Posten>());
					arrBg.add(bg);
				}
				bg.getArrPosten().add(new Posten(rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDouble(7),
						ProduktTyp.valueOf(rs.getString(8))));

				lastBestellung = idBestellung;
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrBg;
	}

	public ArrayList<TischBG> getTischBGG() {
		ArrayList<TischBG> arrTischBG = new ArrayList<TischBG>();
		ArrayList<BestellungGruppiert> arrBg = getBestellungGruppiertG();
		arrBg.sort(new Comparator<BestellungGruppiert>() {
			@Override
			public int compare(BestellungGruppiert bg1, BestellungGruppiert bg2) {
				return bg2.getTischId() - bg1.getTischId();
			}
		});
		
		TischBG tischBG = new TischBG();
		for (BestellungGruppiert bg : arrBg) {
			if (!arrTischBG.stream().anyMatch(tbg -> tbg.getTischId() == bg.getTischId())) {
				tischBG = new TischBG(bg.getTischId(), new ArrayList<BestellungGruppiert>());
				arrTischBG.add(tischBG);
			}
			tischBG.getArrBG().add(bg);
		}

		return arrTischBG;
	}

	public ArrayList<TischBG> getTischBGD() {
		ArrayList<TischBG> arrTischBG = new ArrayList<TischBG>();
		ArrayList<BestellungGruppiert> arrBg = getBestellungGruppiertD();
		arrBg.sort(new Comparator<BestellungGruppiert>() {
			@Override
			public int compare(BestellungGruppiert bg1, BestellungGruppiert bg2) {
				return bg2.getTischId() - bg1.getTischId();
			}
		});
		
		TischBG tischBG = new TischBG();
		for (BestellungGruppiert bg : arrBg) {
			if (!arrTischBG.stream().anyMatch(tbg -> tbg.getTischId() == bg.getTischId())) {
				tischBG = new TischBG(bg.getTischId(), new ArrayList<BestellungGruppiert>());
				arrTischBG.add(tischBG);
			}
			tischBG.getArrBG().add(bg);
		}

		return arrTischBG;
	}
}
