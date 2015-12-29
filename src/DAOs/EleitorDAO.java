package DAOs;

import AAE.Eleitor;
import AAE.Lista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class EleitorDAO implements Map<String,Eleitor> {
    
    public Connection conn;
    
    public void clear () {       
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM eleitor");
            ps.executeUpdate();
        }catch (SQLException | ClassNotFoundException e) { 
        e.printStackTrace(); 
        } finally { 
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean containsKey(Object key) throws NullPointerException {
        boolean c = false;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select `Nr de eleitor` from eleitor where `Nr de eleitor` = '" + (String)key + "'" );
            ResultSet rs = ps.executeQuery();
            c = rs.next();
        } catch (SQLException | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }
        return c;
    }       
    
    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object value) not implemented!");
    }
    
    public Set<Map.Entry<String,Eleitor>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Eleitor>> entrySet() not implemented!");
    }
    
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    public Eleitor get(Object key) {
        Eleitor c = null;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from eleitor where `Nr de eleitor`'" +(String)key +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                PreparedStatement ps1 = conn.prepareStatement("Select * from `Assembleia de voto` where eleitor = '" + key +"'" );
                ResultSet rs2 = ps1.executeQuery();
                int type = -1;
                if (rs2.next()) {

                    if (rs.getBoolean("presidente"))
                        type = Eleitor.presidenteType;
                    if (rs.getBoolean("vice-presidente"))
                        type = Eleitor.vPresidenteType;
                    if (rs.getBoolean("secretario"))
                        type = Eleitor.secType;
                    if (rs.getBoolean("escrutinadores"))
                        type = Eleitor.escType;

                }
                c = new Eleitor(rs.getString("`Nr de eleitor`"),rs.getString("Nome"),rs.getInt("Idade"), rs.getDate("Data"), rs.getString("Distrito"),rs.getString("Concelho"),rs.getString("Freguesia"),type);
            }
        } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return c;
    }
    
    public int hashCode() {
        return this.conn.hashCode();
    }
    
    public boolean isEmpty() {
        boolean b = false;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Eleitor");
            ResultSet rs = ps.executeQuery();
            b = !rs.next();
        } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return b;
    }
    
    public Set<String> keySet() {
        Set<String> res = new HashSet<String>();
        
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT `Nr de eleitor` FROM Eleitor");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getString("Nr de Eleitor"));
            }
	} catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }
        return res;
    }
    
    public Eleitor put(String key, Eleitor value) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Eleitor WHERE `Nr de Eleitor` ='"+key+"'");
            ps.executeUpdate();
            PreparedStatement sql =conn.prepareStatement("INSERT INTO  VALUES ('"+key+"','"+value.getNome()+"','"+value.getIdade()+"','"+value.getDataR()+"','"+value.getDistrito()+"','"+value.getConcelho()+"','"+value.getFreguesia()+"','NULL')");
            sql.executeUpdate();
           } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace();
                return null;
            } 
        }
         return value;
    }

    public void putAll(Map<? extends String,? extends Eleitor> t) {
        throw new NullPointerException("Not implemented!");
    }
    
    public Eleitor remove(Object key) {
        throw new NullPointerException("public Eleitor remove(Object key)  not implemented!");
    }
    
    public void remove(String nrEleitor) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM eleitor WHERE `Nr de Eleitor` =' "+nrEleitor+"'");
            ps.executeUpdate();
        } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }}
    
    public int size() {
        int i = -1;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select count(`Nr de Eleitor`) from Eleitor");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                i=rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }
        return i;
    }
             
    public Collection<Eleitor> values() {
        Collection<Eleitor> res = new ArrayList<Eleitor>();       
        try { 
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Eleitor");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();) {
                PreparedStatement ps1 = conn.prepareStatement("Select * from `Assembleia de voto` where eleitor = '" + rs.getString("Nr de eleitor") +"'" );
                ResultSet rs2 = ps1.executeQuery();
                int type = -1;
                if (rs2.next()) {

                    if (rs.getBoolean("presidente"))
                        type = Eleitor.presidenteType;
                    if (rs.getBoolean("vice-presidente"))
                        type = Eleitor.vPresidenteType;
                    if (rs.getBoolean("secretario"))
                        type = Eleitor.secType;
                    if (rs.getBoolean("escrutinadores"))
                        type = Eleitor.escType;

                }
                res.add(new Eleitor(rs.getString("Nr de eleitor"),rs.getString("Nome"),rs.getInt("Idade"), rs.getDate("Data"), rs.getString("Distrito"),rs.getString("Concelho"),rs.getString("Freguesia"),type));
                }
        } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
      }
        return res;
    } 
    
    public Integer getNParticipantes(String eleicao) throws SQLException{
    	int p=-1;
    	try{
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select count(Votou) from Participantes where eleicao ='" + eleicao +"' AND Votou = 'TRUE'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p = rs.getInt(1);
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return p;
    }

    public ArrayList <Eleitor> getDeputadosLista(String l)
    {
        ArrayList<Eleitor> toRet = new ArrayList<>();
        try{
            conn =SqlConnect.connect();
            PreparedStatement ps1 = conn.prepareStatement("Select * from Eleitor where lista'" + l +"'");
            ResultSet rs = ps1.executeQuery();
            for (;rs.next();){
                if(rs.getBoolean("Deputado"))
                toRet.add(this.get(rs.getString("Nr de Eleitor")));
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return toRet;
    }

    public ArrayList <Eleitor> getDelegadosLista(String l)
    {
        ArrayList<Eleitor> toRet = new ArrayList<>();
        try{
            conn =SqlConnect.connect();
            PreparedStatement ps1 = conn.prepareStatement("Select * from Eleitor where lista'" + l +"'");
            ResultSet rs = ps1.executeQuery();
            for (;rs.next();){
                if(rs.getBoolean("Delegado"))
                    toRet.add(this.get(rs.getString("Nr de Eleitor")));
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return toRet;
    }


    public void putBulk(String s) {

        try{
            conn =SqlConnect.connect();
            PreparedStatement ps1 = conn.prepareStatement(s);
            ps1.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

