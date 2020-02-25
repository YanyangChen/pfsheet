package websams.jsf.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.primefaces.extensions.component.sheet.Sheet;
import org.primefaces.extensions.event.SheetEvent;
import org.primefaces.extensions.model.sheet.SheetUpdate;

import websams.component.handsontable.model.Asset;
import websams.component.handsontable.model.Subjects;
import websams.component.handsontable.model.AssetType;
import websams.component.handsontable.model.PlatformArchType;
import websams.component.handsontable.model.PlatformType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@ManagedBean
@ViewScoped
public class SheetController implements Serializable {

    private static final long serialVersionUID = 20120224L;
    
    private List<Asset> assets = new ArrayList<>();
    private List<Subjects> subjectslist = new ArrayList<>();
    private List<Subjects> oldsubjectslist = new ArrayList<>();
    private List<Subjects> modificationlist = new ArrayList<>();
    private List<Asset> filteredAssets = new ArrayList<>();
    public String macDBLocation = "jdbc:sqlite:/Users/Shared/Work/EDB/pfsheet/websams_pfsheet-master/sample2.db";
    public String EDBpcDBLocation = "jdbc:sqlite:D:\\jsfnestedheader\\handson_workspace\\websams-handsontable\\sample2.db";
    
    public List<Subjects> getSubjectslist() {
		return subjectslist;
	}

	public void setSubjectslist(List<Subjects> subjectslist) {
		this.subjectslist = subjectslist;
	}

	public SheetController() throws Exception {
        addAssets(40, PlatformType.Linux, PlatformArchType.AMD64, AssetType.SERVER);
        addAssets(50, PlatformType.Windows, PlatformArchType.I386, AssetType.DESKTOP);
        addAssets(60, PlatformType.Mac, PlatformArchType.OTHER, AssetType.LAPTOP);
        addAssets(5, null, null, AssetType.PRINTER);
        getSubjectsFromDB();
        //oldsubjectslist = getSubjectsFromDB();
    }

    public void cellChangeEvent(final SheetEvent event) {
        final Sheet sheet = event.getSheet();
        final List<SheetUpdate> updates = sheet.getUpdates();
        // A SheetUpdate exists for each column updated, the same row may
        // appear more than once. For this reason we will track those we already persisted
        final HashSet<Asset> processed = new HashSet<Asset>();
        System.out.println("running cellChangeEvent");
        int rowUpdates = 0;
        for (final SheetUpdate update : updates) {
        	
            final Asset asset = (Asset) update.getRowData();
            if (processed.contains(asset)) {
                continue;
             
            }
            System.out.println("Asset " + asset.getAssetId() + " updated.");
            rowUpdates++;
        }
        sheet.commitUpdates();
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Update Success", Integer.toString(rowUpdates) + " rows updated"));
    }
    
    public void assetChangeEvent(final SheetEvent event) {
        final Sheet sheet = event.getSheet();
        final List<SheetUpdate> assetsupdates = sheet.getUpdates();
        // A SheetUpdate exists for each column updated, the same row may
        // appear more than once. For this reason we will track those we already persisted
        final HashSet<Asset> processed = new HashSet<Asset>();
        System.out.println("running cellChangeEvent");
        int rowUpdates = 0;
        for (final SheetUpdate update : assetsupdates) {
        	
            final Asset modified_asset = (Asset) update.getRowData();
            if (processed.contains(modified_asset)) {
                continue;
             
            }
            System.out.println("Asset " + modified_asset.getAssetId() + " updated.");
            rowUpdates++;
        }
        sheet.commitUpdates();
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Update Success", Integer.toString(rowUpdates) + " rows updated"));
    }
    
    public void subjectsChangeEvent(final SheetEvent event) throws Exception {
    	
        final Sheet sheet = event.getSheet();
        
        final List<SheetUpdate> subjectsupdates = sheet.getUpdates();
        
        //** make the sheet header row counted in here.
//        System.out.println("subjectsupdates' size is : " + subjectsupdates.size());
//        System.out.println("subjectsupdates' toString is : " + subjectsupdates.get(0).toString());
//        System.out.println("subjectsupdates' contains  : " + subjectsupdates.size());
        
        // A SheetUpdate exists for each column updated, the same row may
        // appear more than once. For this reason we will track those we already persisted
        final HashSet<Subjects> processed = new HashSet<Subjects>();
        int rowUpdates = 0;
        System.out.println("running subjectsChangeEvent");
        for (final SheetUpdate update : subjectsupdates) {
            final Subjects modified = (Subjects) update.getRowData();
            if (processed.contains(modified)) {
            	System.out.println(rowUpdates + "not updated");
                continue;
                
            }
//            Class.forName("org.sqlite.JDBC");
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\jsfnestedheader\\handson_workspace\\websams-handsontable\\sample2.db");
//        	//Statement statement = connection.createStatement();
//            String sql = 
//            	    "select * " +
//            	    "from  subject " +
//            	    "where  CHIN = "+ "'" +modified.getChin()+"'"+";";
//            System.out.println("sql = " + sql);
//            Statement stmt = connection.createStatement(); 
//            
//           // stmt.setQueryTimeout(30);
//           
//           
//           //assume that Chin is read only key
////            stmt.setString(1, modified.getChin());
//            ResultSet rs = stmt.executeQuery(sql);
//            rs.updateString("ENG", modified.getEng());
//            rs.updateString("GENS", modified.getGens());
//            rs.updateString("LILE", modified.getLile());
//            rs.updateString("PE", modified.getPe());
//            rs.updateRow();
//            rs.close();
//            stmt.close();
//            connection.close();
            modificationlist.add(modified);
            if(modificationlist.size() >=2) {
            if(modificationlist.get(modificationlist.size()-1).getChin() == modificationlist.get(modificationlist.size()-2).getChin()) {
            	modificationlist.remove(modificationlist.size()-2);
            }
            }
            System.out.println("modified subjects " + modified.getChin() + " updated.");
            rowUpdates++;
        }
        sheet.commitUpdates();
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Update Success", Integer.toString(rowUpdates) + " rows updated"));
    }
    
    public void updateSubjects(Subjects modified) {
        String sql = "UPDATE subject SET ENG = ? , "
                + "GENS = ? , "
                + "LILE = ? , "
                + "PE = ? "
                + "WHERE CHIN = ?";
 
        try (Connection conn = DriverManager.getConnection(macDBLocation);
            	
               PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	System.out.println("starting update statement");
            // set the corresponding param
            pstmt.setString(5, modified.getChin());
            pstmt.setString(1, modified.getEng());
            pstmt.setString(2, modified.getGens());
            pstmt.setString(3, modified.getLile());
            pstmt.setString(4, modified.getPe());
            // update 
            pstmt.executeUpdate();
            System.out.println("update statement executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addAssets(final int count, final PlatformType platform, final PlatformArchType arch, final AssetType type) {
        for (int i = 0; i < count; i++) {
            final Asset asset = new Asset();
            asset.setAssetId(RandomUtils.nextLong());
            asset.setActive(RandomUtils.nextBoolean());
            asset.setPlatform(platform);
            asset.setPlatformArch(arch);
            asset.setHostName(type.toString().toLowerCase() + i + ".example.lan");
            asset.setAssetType(type);
            asset.setPurchaseDate(new Date());
            asset.setPurchaseTime(new Date());
            asset.setValue1(RandomUtils.nextInt(1, 1000));
            asset.setPassword(RandomStringUtils.randomAlphabetic(6));
            asset.setPurchasePrice(
                        BigDecimal.valueOf(RandomUtils.nextDouble(1.11, 999.99) * (RandomUtils.nextBoolean() ? 0 : 1)).setScale(2, RoundingMode.HALF_UP));
            getAssets().add(asset);
        }
    }

    public List<Asset> getAssets() {
        return assets;
    }
    
    public List<Subjects> getSubjectsFromDB() throws Exception {
    	
    	Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(macDBLocation);
    	Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
//	    statement.executeUpdate("drop table if exists subject");
//	    statement.executeUpdate("create table subject (CHIN string, ENG string, GENS string, LILE string, PE string)");
//	    statement.executeUpdate("insert into subject values('chi1', 'eng1', 'gens1', 'lile1', 'pe1')");
//	    statement.executeUpdate("insert into subject values('chi2', 'eng2', 'gens2', 'lile2', 'pe2')");
        ResultSet rs = statement.executeQuery("select * from subject");
        for (Integer i=0; i<2; i++) {
        	final Subjects subjects = new Subjects();
        	
        	subjects.setChin("header" + i.toString());
            subjects.setEng( i.toString());
            subjects.setGens( i.toString());
            subjects.setLile( i.toString());
            subjects.setPe( i.toString());
            getSubjectsList().add(subjects);
        }
        
        while(rs.next())
        {
        final Subjects subjects = new Subjects();
        subjects.setChin(rs.getString("CHIN"));
        subjects.setEng(rs.getString("ENG"));
        subjects.setGens(rs.getString("GENS"));
        subjects.setLile(rs.getString("LILE"));
        subjects.setPe(rs.getString("PE"));
        getSubjectsList().add(subjects);
        }
        rs.close();
        statement.close();
        connection.close();
        
        return subjectslist;
    }
    
    public void updateSubjects2DB() throws Exception {
    	
    		for (Subjects modified : modificationlist)
    		{
    			
    			updateSubjects(modified);
    			System.out.println("modification udpated");
    		}
    	
    }
    

    
    public List<Subjects> getSubjectsList() {
        return subjectslist;
    }

    public void setAssets(final List<Asset> assets) {
        this.assets = assets;
    }

    public List<Asset> getFilteredAssets() {
        return filteredAssets;
    }

    public void setFilteredAssets(final List<Asset> filteredAssets) {
        this.filteredAssets = filteredAssets;
    }

}
            