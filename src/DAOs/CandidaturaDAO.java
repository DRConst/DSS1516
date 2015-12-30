package DAOs;

import AAE.Candidato;
import AAE.Candidatura;

import java.sql.*;
import java.util.*;
import java.util.Date;


@SuppressWarnings("Duplicates")
public class CandidaturaDAO implements Map<String, Candidatura> {
    
    public Connection conn;
    
    public void clear () {       
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidatura");
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
            PreparedStatement ps = conn.prepareStatement("Select nome from candidatura where nome = '" + (String)key + "'" );
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
    
    public Set<Map.Entry<String,Candidatura>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Equipa>> entrySet() not implemented!");
    }
    
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    public Candidatura get(Object key) {
        Candidatura c = null;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from candidato where nome = '" +(String)key +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Candidato candidato = new CandidatoDAO().get(rs.getString("Nome"));
                if (candidato != null) {
                    c = new Candidatura(rs.getInt("Candidatura_id"),rs.getDate("Data"),candidato,rs.getString("Eleicao"));
                }
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidatura");
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
            PreparedStatement ps = conn.prepareStatement("SELECT nome FROM candidatura");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getString("nome"));
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
    
    public Candidatura put(String key, Candidatura value) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidatura WHERE nome='"+key+"'");
            ps.executeUpdate();
            PreparedStatement ps1 = conn.prepareStatement("Select BI from candidato where nome = '"+value.getCandidato()+"'" );
            ResultSet rs = ps1.executeQuery();
            PreparedStatement sql =conn.prepareStatement("INSERT INTO  VALUES ('"+key+"','"+value.getCandidato().getNome()+"','"+value.getData()+"','"+value.getEleicao()+"','"+rs.getInt("BI")+"')");
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

    public void putAll(Map<? extends String,? extends Candidatura> t) {
        throw new NullPointerException("Not implemented!");
    }
    
    public Candidatura remove(Object key) {
        throw new NullPointerException("public AssembleiaDeVoto remove(Object key)  not implemented!");
    }
    
    public void remove(String nome) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidatura WHERE Nome =' "+nome+"'");
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
            PreparedStatement ps = conn.prepareStatement("Select count(id) from candidatura");
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
             
    public Collection<Candidatura> values() {
        ArrayList<Candidatura> ct = new ArrayList<Candidatura>();
       
        try { 
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidatura");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();) {
                Candidato candidato = new CandidatoDAO().get(rs.getString("Nome"));
                if (candidato != null) {
                    Candidatura ca = new Candidatura(rs.getInt("Candidatura_id"),rs.getDate("Data"),candidato,rs.getString("Eleicao"));
                    ct.add(ca);
                }
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
        return ct;
    } 
    
    public ArrayList<String> getCandidaturas(String eleicao) throws SQLException{
    	ArrayList<String> res = new ArrayList<String>();
    	try{
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from candidatura where eleicao ='" + eleicao +"'");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getString("Nome"));
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
        return res;
    }

    public int getAvailableId()
    {
        try{
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select max(candidatura_id) from candidatura;");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            return rs.getInt(1) + 1;
            else return 1;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getNumAssinaturasCandidato(Integer bi)
    {
        int assinaturas;
        try{
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select count(eleitor) from EleitorApoiaCand where candidatura = " + bi + ";");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            return rs.getInt(1);
            else return 0;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return -1;
    }

    
}

