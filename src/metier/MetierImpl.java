package metier;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class MetierImpl implements IMetier {

	@Override
	public void addPersonne(Personne pers) {
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addPersonne(?,?,?)");
			ps.setString(1,pers.getPrenom());
			ps.setString(2,pers.getMail());
			ps.setString(3,pers.getNom() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("la personne ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addEquipe(Equipe equip) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addEquipe(?)");
			
			ps.setString(1,equip.getNom() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("l'equipe ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public SortedSet<Personne> GetAllPersonne() 
	{
		
		SortedSet<Personne> persAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call GetAllPersonne()");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Personne pers = new Personne(null, " ", " ", " ");
				
				pers.setMail(rs.getString("mail"));
				pers.setPrenom(rs.getString("prenom"));
				String nomCandidat = rs.getString("nomCandidat");
				
				PreparedStatement ps2 = conn.prepareStatement
						("call GetoneCandidat(?)");
				ps2.setString(1, nomCandidat);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				Candidat candit = new Personne(null, " ", " ", " ");
				candit.setNom(rs2.getString("nomCandidat"));
				pers.setNom(candit.getNom());
				persAll.add(pers);
				ps2.close();
			}
			
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return persAll;
		
	}

	@Override
	public void addCandidat(Candidat candid) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addCandidat(?)");
			
			ps.setString(1,candid.getNom() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("le candidat ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addCompetition(Competition comp) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addCompetition(?,?,?)");
			
			ps.setString(1,comp.getNom() ); 
			 ps.setDate(2, java.sql.Date.valueOf(comp.getDateCloture()));
			ps.setBoolean(3,comp.estEnEquipe() ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("la competion ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addInscrit(String candid ,String comp)
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addInscrit(?,?)");
			
			ps.setString(1,candid); 
			ps.setString(2,comp ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("l'inscription  ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addPersonneInEquipe(String equipe, String pers) 
	{
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("call addPersonneInEquipe(?,?)");
			
			ps.setString(1,equipe); 
			ps.setString(2,pers ); 
			ps.executeUpdate();
			ps.close();
			System.out.println("la  personne  ajoutÃ© avec succÃ¨s!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public SortedSet<Personne> SerchPersonneParMC(String mc) {
		SortedSet<Personne> persAll = new TreeSet<>();

		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select prenom,mail,nomCandidat from personne,candidat where personne.id_candid = CANDIDAT.id_candid and nomCandidat like ?");
			ps.setString(1,"%"+mc+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Personne pers = new Personne(null, " ", " ", " ");
				
				pers.setMail(rs.getString("mail"));
				pers.setPrenom(rs.getString("prenom"));
				String nomCandidat = rs.getString("nomCandidat");
				
				PreparedStatement ps2 = conn.prepareStatement
						("call GetoneCandidat(?)");
				ps2.setString(1, nomCandidat);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				Candidat candit = new Personne(null, " ", " ", " ");
				candit.setNom(rs2.getString("nomCandidat"));
				pers.setNom(candit.getNom());
				persAll.add(pers);
				ps2.close();
			}
			
			ps.close();
			System.out.println("personne récupérée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return persAll;
	}

	@Override
	public void UpdatePersonne(Personne pers) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("update personne set prenom = ?,mail=? where personne.id_candid in(select id_candid from candidat where nomCandidat = ? )");
			ps.setString(1,pers.getPrenom());
			ps.setString(2,pers.getMail());
			ps.setString(3,pers.getNom());
		    ps.executeUpdate();
			ps.close();
			System.out.println("personne modifiée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void DeletePersonne(String pers) 
	{
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("delete from candidat where nomCandidat = ?");
			ps.setString(1,pers);
		    ps.executeUpdate();
			ps.close();
			System.out.println("personne supprimée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Personne GetPersonne(String pers) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select prenom,mail,nomCandidat from personne,candidat where personne.id_candid = CANDIDAT.id_candid and nomCandidat = ?");
			ps.setString(1,pers ); 
			Personne per = new Personne(null, " ", " ", " ");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				per.setMail(rs.getString("mail"));
				per.setPrenom(rs.getString("prenom"));
				per.setNom(rs.getString("nomCandidat"));
				ps.close();
				System.out.println("personne récupée !!");
				return per;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SortedSet<Equipe> SerchEquipeParMC(String mc) {
		SortedSet<Equipe> EqupeAll = new TreeSet<>();

		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nomCandidat from equipe,CANDIDAT  where equipe.id_candid = CANDIDAT.id_candid  and nomCandidat like ?");
			ps.setString(1,"%"+mc+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Equipe equip = new Equipe(null, " ");
				
				equip.setNom(rs.getString("nomCandidat"));
				EqupeAll.add(equip);
				
			}
			
			ps.close();
			System.out.println("equipe récupérée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return EqupeAll;
	}

	@Override
	public void DeleteEquipe(String equip) 
	{
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("delete from candidat where nomCandidat = ?");
			ps.setString(1,equip);
		    ps.executeUpdate();
			ps.close();
			System.out.println("equipe supprimée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public SortedSet<Equipe> GetAllEquipe() {
		
		SortedSet<Equipe> EquipAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nomCandidat  from equipe,candidat where EQUIPE.id_candid = CANDIDAT.id_candid");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				Equipe equip = new Equipe(null, " ");
				
				equip.setNom(rs.getString("nomCandidat"));				
			
				EquipAll.add(equip);
				
			}
			
			ps.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return EquipAll;
		
	}

	
	@Override
	public void DeletePersonneInEquipe(String equip, String pers) 
	{
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("DELETE FROM  composer  WHERE   nomCandidat = ? AND nomCandidat_CANDIDAT = ?");
			ps.setString(1,equip);
			ps.setString(2,pers);
		    ps.executeUpdate();
			ps.close();
			System.out.println("personne supprimée !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, String> SerchPersonneInEquipeParMC(String mc)
	{
		Map<String, String> persinequip = new HashMap<String, String>();
		ArrayList<String> alCandidat = new ArrayList<String>();
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("call SerchAllPersonneInEquipe2(?)");
			PreparedStatement ps2 = conn.prepareStatement("call SerchAllPersonneInEquipe(?)");
			ps.setString(1,"%"+mc+"%");
			ps2.setString(1,mc);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				alCandidat.add(rs.getString("nomCandidat"));
			}
			
			for(int i = 0; i < alCandidat.size()-1; i++)
		    {
				persinequip.put(alCandidat.get(i),alCandidat.get(i+1));
		      
		    } 
			
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				persinequip.put(rs2.getString("nomCandidat"),"Aucun");
				
			}
			
			ps.close();
			ps2.close();
			System.out.println("persInEquipe trouvée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return persinequip;
		
	}

	@Override
	public Map<String, String> GetAllPersonneInEquipe() {
		
		Map<String, String> persinequip = new HashMap<String, String>();
		ArrayList<String> alCandidat = new ArrayList<String>();

		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("call GetAllPersonneInEquipe()");
			PreparedStatement ps2 = conn.prepareStatement("select nomCandidat from CANDIDAT c,equipe e where c.id_candid = e.id_candid and c.id_candid NOT IN (select id_candid from composer)and c.id_candid NOT IN (select id_candid_CANDIDAT from composer)");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				alCandidat.add(rs.getString("nomCandidat"));
			}
			
			for(int i = 0; i < alCandidat.size()-1; i++)
		    {
				persinequip.put(alCandidat.get(i),alCandidat.get(i+1));
		      
		    } 
			
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next())
			{
				persinequip.put(rs2.getString("nomCandidat"),"Aucun");
				
			}
			
			ps.close();
			System.out.println("persInEquipe récupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return persinequip;
	}

	@Override
	public SortedSet<Competition> GetCompetition() {
		
		SortedSet<Competition> CompetpAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		Competition compet;
		LocalDate date ;
		try {
			PreparedStatement ps = conn.prepareStatement
					(" call GetCompetition()");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				date = LocalDate.parse((rs.getString("dateclose")));
				
				compet = new Competition(null, rs.getString("nom_compet"), date, rs.getBoolean("enEquipe"));
			
				CompetpAll.add(compet);
				
			}
			
			ps.close();
			
			System.out.println("compétition ajoutée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return CompetpAll;
	}
	public SortedSet<Competition> SerchCompetitionParMC(String mc){
		
		SortedSet<Competition> CompetpAll = new TreeSet<>();
		Connection conn = SingletonConnection.getConnection();
		Competition compet;
		LocalDate date ;
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("select nom_compet,dateclose,enEquipe  from COMPETITION where  nom_compet like ?");
			ps.setString(1,"%"+mc+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				date = LocalDate.parse((rs.getString("dateclose")));
				
				compet = new Competition(null, rs.getString("nom_compet"), date, rs.getBoolean("enEquipe"));
			
				CompetpAll.add(compet);
				
			}
			
			ps.close();
			
			System.out.println("compétition récupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return CompetpAll;
	}
	public Competition GetOneCompetition(String nom ){
		
		Connection conn = SingletonConnection.getConnection();
		Competition compet = null;
		LocalDate date ;
		try {
			PreparedStatement ps = conn.prepareStatement
					(" select nom_compet,dateclose,enEquipe  from COMPETITION where nom_compet = ? ");
			ps.setString(1,nom ); 
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				date = LocalDate.parse((rs.getString("dateclose")));
				
				compet = new Competition(null, rs.getString("nom_compet"), date, rs.getBoolean("enEquipe"));
			}
			
			ps.close();
			
			System.out.println("compétition recupérée !!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return compet;
	}

	@Override
	public void UpdateCompetition(Competition compet,String nomcompet) {
		
		Connection conn = SingletonConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("update competition set nom_compet = ?,dateclose= ?, enEquipe = ? where nom_compet = ? ");
			ps.setString(1,compet.getNom());
			ps.setDate(2, java.sql.Date.valueOf(compet.getDateCloture()));
			ps.setBoolean(3,compet.estEnEquipe());
			ps.setString(4,nomcompet);
		    ps.executeUpdate();
			ps.close();
			System.out.println("competition modifiée !!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void UpdateEquipe(Equipe equip,String beforeName) {
		Connection conn = SingletonConnection.getConnection();
		

		try {
			PreparedStatement ps = conn.prepareStatement
					("update candidat set nomCandidat = ? where nom_compet = ? ");
			ps.setString(1,equip.getNom());
			ps.setString(2,beforeName);
			
		    ps.executeUpdate();
			ps.close();
			System.out.println("equipe modifiée !!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
