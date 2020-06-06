<%@page import="java.util.ArrayList"%>
<%@page
	import="br.com.kartRacing.modules.results.infra.data.entities.Result"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kart Racing</title>
</head>
<style>
	table, td, th {
		border: solid 1px #000;
		padding: 5px;
	}
	th {
		background-color: #00BFFF;
	}
	colgroup {
		background:#ADD8E6;
	}
</style>
<body>
	<center>
    	<font face="Arial" size="4">
			<h1>Corrida de Kart</h1>
    		<img style="height: 356px; width: 408px;" src="images/KartRacing.svg" alt="Kart Racing">
			<form action="FileUploadKartServlet" enctype="multipart/form-data" method="post">
				Insira aqui o arquivo de log da corrida:<input type="file"
					name="file2" /><br> <input type="submit" value="Enviar" />
			</form>
			<%
	 			ArrayList<Result> result = (ArrayList<Result>)request.getAttribute("result") != null ? 
	 					(ArrayList<Result>)request.getAttribute("result") : new ArrayList<Result>();
	 			if(result.isEmpty())
	 				out.println("Arquivo inválido");
	 			else {
	 		%>
			<table>
				<colgroup span="8"></colgroup>
				<tr>
					<th>Posição Chegada</th>
					<th>Código Piloto</th>
					<th>Nome Piloto</th>
					<th>Quantidade Voltas Completadas</th>
					<th>Tempo Total de Prova</th>
					<th>Melhor Volta</th>
					<th>Velocidade Média</th>
					<th>Tempo Após Vencedor</th>
				</tr>
				<%
	 				for (int i = 0; i < result.size(); i++) {  
			%>
				<tr>
					<td><%=result.get(i).getPosition()%></td>
					<td><%=result.get(i).getPilotCode()%></td>
					<td><%=result.get(i).getPilotName()%></td>
					<td><%=result.get(i).getNumberLaps()%></td>
					<td><%=result.get(i).getTrialTime()%></td>
					<td><%=result.get(i).getBestLap().getNumber()%></td>
					<td><%=String.valueOf(result.get(i).getAverageSpeedPilot())%></td>
					<td><%=result.get(i).getTimeAfterWinner()%></td>
				</tr>
				<%
	 	 			}
	 			}
	 		%>
			</table>
		</font>
	</center>
</body>
</html>