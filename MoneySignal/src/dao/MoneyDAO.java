package dao;

import static dbmanager.JdbcConnect.getConnection;
import static dbmanager.JdbcConnect.commit;
import static dbmanager.JdbcConnect.rollback;
import static dbmanager.JdbcConnect.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import dbmanager.JdbcConnect;
import dto.CommDTO;
import dto.ObankDTO;
import dto.PayDTO;
import dto.UserDTO;
import view.PieElement;

public class MoneyDAO {
   
   //로그인 함수
   public UserDTO loginUser(String id,String pwd){
      Connection conn=getConnection();
      PreparedStatement pst=null;
      ResultSet rs=null;
      //List<UserDTO> list=new ArrayList<>();
      UserDTO dto=null;
      
      try {
         String sql="SELECT UPASSWORD,UNAME,UACCOUNT FROM USERDTO WHERE USERID=?";
         pst=conn.prepareStatement(sql);
         pst.setString(1, id);
         
         rs=pst.executeQuery();
         
         while(rs.next()) {
            dto=new UserDTO();
            dto.setU_pwd(rs.getString("UPASSWORD"));
            dto.setU_name(rs.getString("UNAME"));
            dto.setU_account(rs.getLong("UACCOUNT"));
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pst);
         close(conn);
      }
      return dto;
   }

   //로그인해서 user정보를 가져옵니다.
   public Map<String,Integer> totalPayment(long account) {
      Connection conn=getConnection();
      PreparedStatement pst=null;
      ResultSet rs=null;
      
      Map<String,Integer> map=new HashMap<>();
      try {
         String sql="SELECT PDETAIL,SUM(PPAYMENT) FROM PAYDTO WHERE PACCOUNT=? GROUP BY PDETAIL";
         pst=conn.prepareStatement(sql);
         pst.setLong(1, account);
      
         rs=pst.executeQuery();
         
         //System.out.println(rs.next());
         while(rs.next()) {
            //System.out.println(rs.getString(1)+" "+rs.getInt(2));
            map.put(rs.getString(1), rs.getInt(2));
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pst);
         close(conn);
      }
      return map;
   }
   
   //3.이체
   public int transfer(PayDTO dto) {
      Connection conn=getConnection();
      PreparedStatement pst=null;
      int n=0;
      
      try {
         String sql="INSERT INTO PAYDTO VALUES(?,?,?,?)";
         pst=conn.prepareStatement(sql);
         pst.setLong(1, dto.getP_account());
         pst.setInt(2, dto.getP_payment());
         pst.setString(3, dto.getP_detail());
         pst.setString(4, dto.getP_memo());
         
         n=pst.executeUpdate();
         
         if(n>0) {
            commit(conn);//insert가 되었기 때문에 commit으로 디비를 바꿔준다
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         rollback(conn);
      }finally {
         close(pst);
         close(conn);
      }
      
      return n;
   }
   
   //5.내 계좌 상세보기
   public Vector<PayDTO> detail(long id){
     Connection conn=getConnection();
     PreparedStatement pst=null;
     ResultSet rs=null;
     Vector<PayDTO> list=new Vector<>();
     PayDTO dto=null;
         
     try {
        String sql="SELECT ACODATE,PPAYMENT,PDETAIL,PMEMO,PCATEGORY  FROM PAYDTO WHERE PACCOUNT=?";
        pst=conn.prepareStatement(sql);
        pst.setLong(1, id);
        rs=pst.executeQuery();
            
        while(rs.next()) {
          dto=new PayDTO();
          //dto.setP_account(rs.getLong("PACCOUNT"));
          dto.setAco_date(rs.getDate("acodate"));
          dto.setP_payment(rs.getInt("PPAYMENT"));
          dto.setP_detail(rs.getString("PDETAIL"));
          dto.setP_memo(rs.getString("PMEMO"));
          dto.setP_category(rs.getString("PCATEGORY"));
          list.add(dto);
        }
     } catch (SQLException e) {
            // TODO Auto-generated catch block
        e.printStackTrace();
     }finally {
        close(pst);
        close(conn);
     }
         return list;
   }

   //회원 가입
   public int signUp(String uname, String userid, String upassword) {
      Connection conn=getConnection();
      PreparedStatement pst=null;
      int n=0;
      
      try {
         String sql="INSERT INTO USERDTO VALUES(?,?,?,UACCOUNT_NUM.NEXTVAL,'시그널은행','정상')";
         pst=conn.prepareStatement(sql);
         pst.setString(1, userid);
         pst.setString(2, upassword);
         pst.setString(3, uname);
         
         n=pst.executeUpdate();
         if(n>0) {
            commit(conn);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         rollback(conn);
      }finally {
         close(pst);
         close(conn);
      }
      return n;
   }
   public int NewMember_checked(String string) {
      Connection conn=getConnection();
      PreparedStatement pst=null;
      ResultSet rs=null;
      int x=0;
      try {
         String sql="SELECT * FROM USERDTO WHERE USERID=?";
         pst=conn.prepareStatement(sql);
         pst.setString(1, string);
         rs=pst.executeQuery();
         
         if(rs.next()) {
            x=1;
         }else {
            x=-1;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pst);
         close(conn);
      }
      return x;

   }
   //이체시 타은행 정보불러오기
   public int bankchecked(String text, String string, String string2) {
      Connection conn=getConnection();
      PreparedStatement pst=null;
      ResultSet rs=null;
      ObankDTO odto=null;
      int x=1;
      try {
         String sql="SELECT * FROM OTHERBANK WHERE OACCOUNT=? AND OBANK=? AND ONAME=?";
         pst=conn.prepareStatement(sql);
         pst.setString(1, text);
         pst.setString(2, string);
         pst.setString(3, string2);
         rs=pst.executeQuery();
         
         if(rs.next()) {
             odto= new ObankDTO();
             odto.setO_account(rs.getLong(1));
             odto.setO_bank(rs.getString(2));
             odto.setO_name(rs.getString(3));
             System.out.println(rs.getLong(1)+" "+rs.getString(2)+" "+rs.getString(3));
             x=1;                     
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pst);
         close(conn);
      }
      
      return x;
   }

   
   // 로그인시 공용 계좌 여부 확인 
public CommDTO Login_Commdto (String id) {
   Connection conn =getConnection();
   PreparedStatement pstmt=null;
   ResultSet rs= null;
   CommDTO cdto=null;
   
   try {
      
      String sql="SELECT * FROM COMMDTO WHERE FC_USER=? OR CS_USER=?";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, id);
      rs=pstmt.executeQuery();
      if(rs.next()) {
         cdto=new CommDTO();
         cdto.setC_account(rs.getLong(1));
         cdto.setC_limit(rs.getInt(2));
         cdto.setCf_user(rs.getString(3));
         cdto.setCs_user(rs.getString(4));
         cdto.setC_pwd(rs.getString(5));
         cdto.setC_save(rs.getFloat(6));
         
      }
      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pstmt);
      close(conn);
   }

   
   return cdto;
}

//고용 계좌 확인
public CommDTO searchcommdto(String text,String string) {
   Connection conn =getConnection();
   PreparedStatement pstmt=null;
   ResultSet rs= null;
   CommDTO cdto=null;
   
   try {
      
      String sql="SELECT * FROM COMMDTO WHERE CACCOUNT=? AND CPWD=? ";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, text);
      pstmt.setString(2, string);
      rs=pstmt.executeQuery();
      if(rs.next()) {
         cdto=new CommDTO();
         cdto.setC_account(rs.getLong(1));
         cdto.setC_limit(rs.getInt(2));
         cdto.setCf_user(rs.getString(3));
         cdto.setCs_user(rs.getString(4));
         cdto.setC_pwd(rs.getString(5));
         cdto.setC_save(rs.getFloat(6));
      
         
      }
      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pstmt);
      close(conn);
   }

   
   return cdto;
}
// 계좌 생성 버튼 클리시 계좌 번호 생성
public int C_insertaccount(String id) {
    Connection conn=getConnection();
     PreparedStatement pst=null;
    
     int n=0;
  
     try {
        String sql="INSERT INTO COMMDTO(CACCOUNT,FC_USER)  VALUES(UACCOUNT_NUM.NEXTVAL,?)";
        pst=conn.prepareStatement(sql);
        pst.setString(1, id);
        n=pst.executeUpdate();
        
        if(n>0) {
           commit(conn);
        }
     } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        rollback(conn);
     }finally {
        close(pst);
        close(conn);
     }
     return n;
  }

//계좌 생성창에서 취소번튼 누리면 그계좌 삭제
public int deleteC_account(String id) {
   Connection conn=getConnection();
    PreparedStatement pst=null;
    int n=0;
 
    try {
       String sql="delete from COMMDTO where Fc_user=?  ";
       pst=conn.prepareStatement(sql);
       pst.setString(1, id);
       n=pst.executeUpdate();
       
       if(n>0) {
          commit(conn);
       }
    } catch (SQLException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       rollback(conn);
    }finally {
       close(pst);
       close(conn);
    }
    return n;
 }

//개설 버튼 클릭시 개설자 아이디 계좌 번호 생성후 계좌 생성창에 계좌 표시
public CommDTO Basic2_callCdata(String id) {
   Connection conn =getConnection();
   PreparedStatement pstmt=null;
   ResultSet rs= null;
   CommDTO cdto=null;
   
   try {
      
      String sql="SELECT * FROM COMMDTO WHERE FC_USER=?";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, id);
      rs=pstmt.executeQuery();
      
      if(rs.next()) {
         cdto=new CommDTO();
         cdto.setC_account(rs.getLong(1));
         cdto.setC_limit(rs.getInt(2));
         cdto.setCf_user(rs.getString(3));
         cdto.setCs_user(rs.getString(4));
         cdto.setC_pwd(rs.getString(5));
         cdto.setC_save(rs.getFloat(6));
          
         
      }
      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pstmt);
      close(conn);
   }

   
   return cdto;
}

//공용 계좌 만들기
public int createC_account(Float rate, int limit, String pwd, String id) {
   Connection conn=getConnection();
    PreparedStatement pst=null;
    int n=0;
try {
      
      String sql="UPDATE COMMDTO SET C_LIMIT=?,CPWD=?, C_SAVE=? WHERE FC_USER=? OR CS_USER=?";
      pst=conn.prepareStatement(sql);
      pst.setInt(1,(limit*10000) );
      pst.setString(2, pwd);
      pst.setFloat(3, rate);
      pst.setString(4, id);
      pst.setString(5, id);
      n=pst.executeUpdate();

      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pst);
      close(conn);
   }

   
   return n;
}

// 고용 계좌에 참여 
public int partici_commdto(String account, String pwd, String id) {
      Connection conn=getConnection();
       PreparedStatement pst=null;
       int n=0;
   try {
         
         String sql="UPDATE COMMDTO SET CS_USER=? WHERE CACCOUNT=? AND CPWD=?";
         pst=conn.prepareStatement(sql);
         pst.setString(1,id );
         pst.setString(2, account);
         pst.setString(3, pwd);
         n=pst.executeUpdate();

         
      }catch (SQLException e) {
         // TODO: handle exception
      }finally {
         close(pst);
         close(conn);
      }

      
      return n;
   }

public int Caccount_PayDTO(int caccount) {
   // TODO Auto-generated method stub
   return 0;
}


public int transmoney(String acc, int pay, String detail, String memo, String category) {
   Connection conn = getConnection();
   PreparedStatement pst = null;
   int n = 0;

   try {

      String sql = "INSERT INTO PAYDTO VALUES(?,?,?,?,?,TO_date(SYSDATE,'YYYY-MM-DD HH24:MI:SS'))";
      System.out.println("asdaaaaaa");
      pst = conn.prepareStatement(sql);
      pst.setString(1, acc);
      pst.setInt(2, pay);
      pst.setString(3, detail);
      pst.setString(4, memo);
      pst.setString(5, category);
      n = pst.executeUpdate();

      if (n > 0) {
         commit(conn);
      }

   } catch (SQLException e) {
      rollback(conn);
   } finally {
      close(pst);
      close(conn);
   }

   return n;
}

public int transmoney2(long u_acc, float u_pay, String u_detail, String u_memo, String u_category) {
   Connection conn = getConnection();
   PreparedStatement pst = null;
   int n = 0;

   try {

      String sql = "INSERT INTO PAYDTO VALUES(?,?,?,?,?,TO_date(SYSDATE,'YYYY-MM-DD HH24:MI:SS'))";
      System.out.println("asdaaaaaa");
      pst = conn.prepareStatement(sql);
      pst.setLong(1, u_acc);
      pst.setFloat(2, u_pay);
      pst.setString(3, u_detail);
      pst.setString(4, u_memo);
      pst.setString(5, u_category);
      n = pst.executeUpdate();

      if (n > 0) {
         commit(conn);
      }

   } catch (SQLException e) {
      rollback(conn);
   } finally {
      close(pst);
      close(conn);
   }

   return n;
}

public int transmoney3(Long c_acc, float c_pay, String c_detail, String c_memo, String c_category) {
   Connection conn = getConnection();
   PreparedStatement pst = null;
   int n = 0;

   try {

      String sql = "INSERT INTO PAYDTO VALUES(?,?,?,?,?,TO_date(SYSDATE,'YYYY-MM-DD HH24:MI:SS'))";
      System.out.println("asdaaaaaa");
      pst = conn.prepareStatement(sql);
      pst.setLong(1, c_acc);
      pst.setFloat(2, c_pay);
      pst.setString(3, c_detail);
      pst.setString(4, c_memo);
      pst.setString(5, c_category);
      n = pst.executeUpdate();
        
      if (n > 0) {
         commit(conn);
      }

   } catch (SQLException e) {
      rollback(conn);
   } finally {
      close(pst);
      close(conn);
   }

   return n;

}

  //공동계좌의 카테고리별 금액 뽑아내기 - 인자 : 공동계좌번호
  public ArrayList<PieElement> commCateDetail(long commAccount){
     Connection conn=getConnection();
      PreparedStatement pst=null;
      ResultSet rs=null;
      //List<UserDTO> list=new ArrayList<>();
      ArrayList<PieElement> list=new ArrayList<PieElement>();
      
      try {
         String sql="SELECT nvl(pcategory,'기타'),sum(ppayment) FROM payDTO "
               + "WHERE paccount=? and pdetail!='입금' group by pcategory";
         pst=conn.prepareStatement(sql);
         pst.setLong(1, commAccount);
         
         rs=pst.executeQuery();
         
         while(rs.next()) {
           list.add(new PieElement(rs.getString(1), rs.getInt(2)));
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         close(pst);
         close(conn);
      }
     
   return list;
  }
  // 회원탈퇴---------------------------------------
  public int delete_user(String id) {
     Connection conn = getConnection();
     PreparedStatement pst = null;
     int n = 0;

     try {
        String sql = "DELETE FROM USERDTO WHERE USERID=?  ";
        pst = conn.prepareStatement(sql);
        pst.setString(1, id); //첫번째 물음표에 뒤에 id값을 넣겠다.
        n = pst.executeUpdate();   //실행이 됐는지 안됐는지가 숫자값으로 반환됨

        if (n > 0) {
           commit(conn);
        }
     } catch (SQLException e) {
        e.printStackTrace();
        rollback(conn);
     } finally {
        close(pst);
        close(conn);
     }
     return n;
  }
  
//공동계좌탈퇴--------------------------------------

public int delete_comm(String id) {
    Connection conn = getConnection();
    PreparedStatement pst = null;
    int n = 0;

    try {
       String sql = "DELETE FROM COMMDTO WHERE FC_USER=?  ";
       pst = conn.prepareStatement(sql);
       pst.setString(1, id); //첫번째 물음표에 뒤에 id값을 넣겠다.
       n = pst.executeUpdate();   //실행이 됐는지 안됐는지가 숫자값으로 반환됨

       if (n > 0) {
          commit(conn);
       }
    } catch (SQLException e) {
       e.printStackTrace();
       rollback(conn);
    } finally {
       close(pst);
       close(conn);
    }
    return n;
 }

public ArrayList<UserDTO> ad_search(String u_name) {
   Connection conn =getConnection();
   PreparedStatement pstmt=null;
   ArrayList<UserDTO> list= new ArrayList<UserDTO>();
   UserDTO udto=null;
   ResultSet rs= null;
   try {
      
      String sql="SELECT UACCOUNT FROM USERDTO WHERE UNAME=?";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, u_name);
      rs=pstmt.executeQuery();
      while(rs.next()) {
         udto=new UserDTO();
         udto.setU_account(rs.getLong(1));
         list.add(udto);
      }
      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pstmt);
      close(conn);
   }

   
   return list;
}

public Vector<PayDTO> three_pdto() {
   Connection conn =getConnection();
   PreparedStatement pstmt=null;
   Vector<PayDTO> list= new Vector<PayDTO>();
   PayDTO pdto=null;
   ResultSet rs= null;

   try {
      
      String sql="SELECT * FROM PAYDTO ";
      pstmt=conn.prepareStatement(sql);
      
      rs=pstmt.executeQuery();
      
      while(rs.next()) {
         pdto=new PayDTO();
         pdto.setP_account(rs.getLong(1));
         pdto.setP_payment(rs.getInt(2));
         pdto.setP_detail(rs.getString(3));
         pdto.setP_memo(rs.getString(4));
         pdto.setP_category(rs.getString(5));
         pdto.setAco_date(rs.getDate(6));
         list.add(pdto);
      }
      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pstmt);
      close(conn);
   }

   
   return list;
}

public Vector<PayDTO> date_search(String text, String text2) {
   Connection conn =getConnection();
   PreparedStatement pstmt=null;
   Vector<PayDTO> list= new Vector<PayDTO>();
   PayDTO pdto=null;
   ResultSet rs= null;

   try {
      
      String sql="SELECT * FROM PAYDTO WHERE acodate between to_date(?,'yyyy-mm-dd')and to_date(?,'yyyy-mm-dd') ";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, text);
      pstmt.setString(2, text2);
      rs=pstmt.executeQuery();
      
      while(rs.next()) {
         pdto=new PayDTO();
         pdto.setP_account(rs.getLong(1));
         pdto.setP_payment(rs.getInt(2));
         pdto.setP_detail(rs.getString(3));
         pdto.setP_memo(rs.getString(4));
         pdto.setP_category(rs.getString(5));
         pdto.setAco_date(rs.getDate(6));
         list.add(pdto);
      }
      
   }catch (SQLException e) {
      // TODO: handle exception
   }finally {
      close(pstmt);
      close(conn);
   }

   
   return list;
}

}
