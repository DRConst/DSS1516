package DAOs;

import AAE.Candidato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CandidatoDAO implements Map<Integer, Candidato> {
    
    public Connection conn;

        public void clear () {       
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidato");
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
            PreparedStatement ps = conn.prepareStatement("Select BI from candidato where BI = '" + (Integer)key + "'" );
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
    
    public Set<Map.Entry<Integer,Candidato>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Integer,Candidato>> entrySet() not implemented!");
    }
    
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    public Candidato get(Object key) {
        ArrayList<Candidatura> ct = new ArrayList<Candidatura>();
        Candidato c = null;
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select * from candidato where BI'" +(Integer)key +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            int id = rs.getInt("BI");
                PreparedStatement ps1 = conn.prepareStatement("Select * from candidaturas where candidato = '" + id +"'" );
            	ResultSet rs2 = ps1.executeQuery();
            	for(;rs2.next();){
                    Candidatura ca = new Candidatura(rs2.getInt("Candidatura_id"),rs2.getDate("Data"),rs2.getString("Eleicao"));
                    ct.add(ca);
                }
                c = new Candidato(rs.getInt("BI"),rs.getString("Nome"),rs.getDate("Data_bi"),rs.getString("Filiacao"),rs.getString("Arquivo"),rs.getString("Profissao"),rs.getInt("Idade"),rs.getString("Morada"),rs.getString("Nacionalidade"), ct);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidato");
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
    
    public Set<Integer> keySet() {
        Set<Integer> res = new HashSet<Integer>();
        
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT BI FROM candidato");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                res.add(rs.getInt("BI"));
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
    
    public Candidato put(Integer key, Candidato value) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidato WHERE BI='"+key+"''");
            ps.executeUpdate();
            PreparedStatement sql =conn.prepareStatement("INSERT INTO  VALUES ('"+key+"','"+value.getNome()+"','"+value.getData_bi()+"','"+value.getFiliacao()+"','"+value.getProfissao()+"','"+value.getIdade()+"','"+value.getMorada()+"','"+value.getNacionalidade()+"')");
            sql.executeUpdate();
           } catch (SQLException  | ClassNotFoundException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                conn.close();     
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        }
         return new Candidato(key,value.getNome(),value.getData_bi(),value.getFiliacao(),value.getProfissao(),value.getIdade(),value.getMorada(),value.getNacionalidade());
    }

    public void putAll(Map<? extends Integer,? extends Candidato> t) {
        throw new NullPointerException("Not implemented!");
    }
    
    public Candidato remove(Object key) {
        throw new NullPointerException("public Candidato remove(Object key)  not implemented!");
    }
    
    public void remove(Integer Bi) {
        try {
            conn = SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidato WHERE BI =' "+Bi+"'");
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
            PreparedStatement ps = conn.prepareStatement("Select count(BI) from candidato");
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
             
    public Collection<Candidato> values() {
        Collection<Candidato> res = new ArrayList<Candidato>();
        ArrayList<Candidatura> ct = new ArrayList<Candidatura>();
       
        try { 
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidato");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();) {
            	int id = rs.getInt("BI");
                PreparedStatement ps1 = conn.prepareStatement("Select * from Candidatura where candidato = '" + id +"'" );
            	ResultSet rs2 = ps1.executeQuery();
            	for(;rs2.next();){
                    Candidatura ca = new Candidatura(rs2.getInt("Candidatura_id"),rs2.getDate("Data"),rs2.getString("Eleicao"));
                    ct.add(ca);
                }
                res.add(new Candidato(rs.getInt("BI"),rs.getString("Nome"),rs.getDate("Data_bi"),rs.getString("Filiacao"),rs.getString("Arquivo"),rs.getString("Profissao"),rs.getInt("Idade"),rs.getString("Morada"),rs.getString("Nacionalidade"), ct));
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
    
    public ArrayList<String> getCandidatos(String eleicao) throws SQLException{
    	ArrayList<String> res = new ArrayList<String>();
    	try{
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select candidato_bi from Eleicao_Candidatos where eleicao ='" + eleicao +"'");
            ResultSet rs = ps.executeQuery();
            for (;rs.next();){
                PreparedStatement ps1 = conn.prepareStatement("Select nome from candidato where BI ='" + rs.getInt("BI")+ "'");
        	ResultSet rs1 = ps.executeQuery();
                for(;rs1.next();){
                    res.add(rs1.getString("Nome"));
                }
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
    
    public Integer getVotos(Integer BI, String nome){
        int votos = 0;
        try{
            conn =SqlConnect.connect();
            PreparedStatement ps = conn.prepareStatement("Select votos from Eleicao_Candidatos where eleicao ='" + nome +"' and  Candidato_BI = '"+ BI.toString() + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                votos = rs.getInt("Votos");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return votos;
    }
    
}

