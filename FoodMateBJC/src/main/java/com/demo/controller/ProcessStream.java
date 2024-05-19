package com.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// ProcessStream 클래스는 InputStream을 읽고 처리하는 역할. 이를 통해 외부 프로세스의 출력이나 오류 스트림을 처리할 수 있음.
public class ProcessStream implements Runnable {
	
	// UTF-8 문자 인코딩을 사용하기 위한 상수
	private static final String UTF_8 = "utf-8";
	private String name; // 스트림의 이름 (STD_IN 또는 STD_ERR)
	private InputStream inputStream; // 처리할 InputStream
	private Thread thread; // 스트림을 비동기적으로 처리하기 위한 스레드
	StringBuilder sb = new StringBuilder(); // 스트림의 출력을 저장할 StringBuilder
	
	// 생성자: 스트림의 이름과 InputStream을 초기화
	public ProcessStream(String name, InputStream inputStream) {
		this.name = name;
		this.inputStream = inputStream;
	}
	
	// 스트림 처리를 시작하는 메서드
	public String start() throws InterruptedException {
		// 새로운 스레드를 생성하고 시작
		this.thread = new Thread(this);
		this.thread.start();
		// 스레드가 작업을 완료할 때까지 대기
		this.thread.join();
		
		// StringBuilder에 저장된 결과를 반환
		return sb.toString();
	}
	
	// Runnable 인터페이스의 run 메서드: 스레드가 실행할 코드
	@Override
	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		String lines = "";
		
		try {
			// InputStream을 UTF-8 인코딩의 InputStreamReader로 감싸고, 이를 다시 BufferedReader로 감쌈
			isr = new InputStreamReader(inputStream, UTF_8);
			br = new BufferedReader(isr);
			
			// InputStream으로부터 한 줄씩 읽어서 StringBuilder에 저장
			while(true) {
				String line = br.readLine();
				// 디버그를 위해 각 라인을 출력하는 코드 (삭제된 상태)
				// System.out.println(line);
				if (line == null)
					break;
				
				// 한 줄을 읽고 StringBuilder에 추가
				lines += line;
				lines += "\n";
			}
			
			// 읽은 내용이 있을 경우 StringBuilder에 추가
			if (!lines.equals("")) {
				// 디버그를 위해 읽은 내용을 출력하는 코드 (주석 처리된 상태)
				// System.out.println("[" + name + "]");
				// System.out.println(lines);
				sb.append(lines);
			}
		} catch (IOException e) {
			// IOException 발생 시 스택 트레이스를 출력
			e.printStackTrace();
		} finally {
			// BufferedReader와 InputStream을 닫아 자원 해제
			try {
				if (br != null)
					br.close();
				
				if (inputStream != null)
					inputStream.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// StringBuilder에 저장된 결과를 반환하는 메서드
	public String getResult() {
		return sb.toString();
	}
}
