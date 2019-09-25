package Biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import dao.MoneyDAO;
import dto.CommDTO;
import dto.PayDTO;
import dto.UserDTO;
import view.PieElement;

public class MoneyBiz {
   MoneyDAO dao = new MoneyDAO(); // 어짜피 모든 함수에서 dao 객체 만들기 때문에 전역변수로 빼놨음

   public int signUp(String uname, String userid, String upassword) {
      int n = dao.signUp(uname, userid, upassword);
      return n;
   }

   public UserDTO loginUser(String id, String pwd) {
      UserDTO list = dao.loginUser(id, pwd);
      return list;
   }

   // 변경 전: MoneyDAO 에서 전부 계산하고 MoneyBiz는 값을 view 화면으로 넘겨주는 역할
   // 변경 후: MoneyDAO 에서 데이터 불러오고 계산은 MoneyBiz에서 하고 바로 view화면으로 넘겨줌
   public int totalPayment(long account) {
      int total = 0;
      Map<String, Integer> map = dao.totalPayment(account);
//      System.out.println(map.get("입금")+" "+map.get("출금")+" "+map.get("카드"));
      if (map != null && map.isEmpty() == false) {
         int x,y,z;
         if(map.get("입금")==null) {
            x=0;
         }else {
            x=map.get("입금");
         }
         if(map.get("출금")==null) {
            y=0;
         }else {
            y=map.get("출금");
         }
         if(map.get("카드")==null) {
            z=0;
         }else {
            z=map.get("카드");
         }
         total=x-(y+z);
      } else {
         total = 0;
      }
      return total;
   }

   public Vector<PayDTO> detail(long account_id) {
      Vector<PayDTO> list = dao.detail(account_id);
      return list;
   }

   public int transfer(PayDTO dto) {
      int n = dao.transfer(dto);
      return n;
   }

   public int NewMember_checked(String string) {

      int x = dao.NewMember_checked(string);

      return x;
   }

   public int bankchecked(String text, String string, String string2) {
      int x = dao.bankchecked(text, string, string2);
      int y = 0;
      if (x == 1) {
         y = 1;
      } else {
         y = -1;
      }
      return y;
   }

   public CommDTO Login_Commdto(String id) {
      CommDTO cdto = dao.Login_Commdto(id);
   
      return cdto;
   }

   public CommDTO searchcommdto(String text,String string) {
      System.out.println("sad");
      CommDTO cdto = dao.searchcommdto(text,string);
      
      return cdto;
   }

   public int C_insertaccount(String id) {
      
      int n = dao.C_insertaccount(id);
      
      return n;
   }

   public int deleteC_account(String id) {
      int n =dao.deleteC_account(id);
      return n;
   }

   public CommDTO Basic2_callCdata(String id) {
      CommDTO cdto = dao.Basic2_callCdata(id);
      return cdto;
   }

   public int createC_account(Float rate, int limit, String pwd, String id) {
      int n =dao.createC_account(rate,limit,pwd,id);
      return n;
   }

   public int partici_commdto(String account, String pwd, String id) {
      int n= dao.partici_commdto(account,pwd,id);
      return n;
   }

   public int Caccount_PayDTO(int caccount) {
      int n= dao.Caccount_PayDTO(caccount);
      return n;
   }

   public int transmoney(String acc, int pay, String detail, String memo, String category) {
       
      int n= dao.transmoney(acc,pay,detail,memo,category);
      
         return n;
   }
   
   public int transmoney2(long u_acc, float u_pay, String u_detail, String u_memo, String u_category) {
      int n= dao.transmoney2(u_acc,u_pay,u_detail,u_memo,u_category);
      return n;
   }

   public int transmoney3(Long c_acc, float c_pay, String c_detail, String c_memo, String c_category) {
       int n=dao.transmoney3(c_acc, c_pay, c_detail, c_memo, c_category);
      return n;
   }
   
   public ArrayList<PieElement> commCateDetail(long commAccount){
      ArrayList<PieElement> map=dao.commCateDetail(commAccount);
      return map;
   }
   
   public int delete_user(String id) {
      int n=dao.delete_user(id);
      return n;
   }
   
   public int delete_comm(String id) {
      int n=dao.delete_comm(id);
      return n;
   }

   public ArrayList<UserDTO> ad_search(String u_name) {
      System.out.println("aaaa");
      ArrayList<UserDTO> list=dao.ad_search(u_name);
      System.out.println("bb");
      return list;
   }

   public Vector<PayDTO> three_pdto() {
       Vector<PayDTO> list = dao.three_pdto();
      return list;
   }

   public Vector<PayDTO> date_search(String text, String text2) {
      Vector<PayDTO> list= dao.date_search(text, text2);
      return list;
   }


}