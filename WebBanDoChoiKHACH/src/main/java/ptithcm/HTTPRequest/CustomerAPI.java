package ptithcm.HTTPRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ptithcm.bean.CustomerInput;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.KHACHHANG;

public class CustomerAPI {
	static HttpURLConnection con;

	public static boolean saveKhachHang(KHACHHANG kh) {
		String str = "http://localhost:8082/createCus";
		try {
			URL url = new URL(str);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonInputString = ow.writeValueAsString(kh);
//			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//            dos.writeBytes(jsonInputString);
			con.getOutputStream().write(jsonInputString.getBytes());
			int responseCode = con.getResponseCode();
			System.out.println("POST Response KH Code :: " + responseCode);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updateCus(KHACHHANG kh) {
		String str = pubVar.apiString + "updateCus/" + kh.getMaKH() + "";
		try {
			URL url = new URL(str);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonInputString = ow.writeValueAsString(kh);
//			DataOutputStream dos = new DataOutputStream(con.getOutputStream());
//            dos.writeBytes(jsonInputString);
			con.getOutputStream().write(jsonInputString.getBytes());
			int responseCode = con.getResponseCode();
			System.out.println("PUT Response KH Code :: " + responseCode);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static KHACHHANG findKHByTK(String tk) {
		KHACHHANG kh = null;
		CustomerInput cus = null;
		String str = pubVar.apiString + "findKHByTK/" + tk + "";
		try {
			URL url = new URL(str);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
				if (response.toString().trim().equals("") == false) {
					ObjectMapper mapper = new ObjectMapper();
					cus = mapper.readValue(response.toString(), CustomerInput.class);
				}
			} else {
				System.out.println(responseCode);
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (cus != null) {
			kh = new KHACHHANG();
			kh.setMaKH(cus.getMaKH());
			kh.setHoTen(cus.getHoTen());
			kh.setDiaChi(cus.getDiaChi());
			kh.setEmail(cus.getEmail());
			kh.setSdt(cus.getSdt());
			kh.setTaiKhoan(cus.getTaiKhoan());
		}
		return kh;
	}

	public static KHACHHANG findKHByID(int tk) {
		KHACHHANG kh = null;
		CustomerInput cus = null;
		String str = pubVar.apiString + "findKHByID/" + tk + "";
		try {
			URL url = new URL(str);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
				if (response.toString().trim().equals("") == false) {
					ObjectMapper mapper = new ObjectMapper();
					cus = mapper.readValue(response.toString(), CustomerInput.class);
				}
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (cus != null) {
			kh = new KHACHHANG();
			kh.setMaKH(cus.getMaKH());
			kh.setHoTen(cus.getHoTen());
			kh.setDiaChi(cus.getDiaChi());
			kh.setEmail(cus.getEmail());
			kh.setSdt(cus.getSdt());
			kh.setTaiKhoan(cus.getTaiKhoan());
		}
		return kh;
	}

}
