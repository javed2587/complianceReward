
<html>
	<head>

		<title>EM IVR input test page</title>

		<style type="text/css">
.report-heading-class {
	font-family: verdana;
	font-size: 15pt;
	font-weight: bold;
	color: #043A88;
}

.input-class {
	font-family: verdana;
	font-size: 10pt;
	color: #043A88;
}

.even-row-class {
	background-color: #FFFFFF;
}

.even-row-class td {
	font-family: verdana;
	font-size: 8pt;
	border-right: solid 1px #888888;
	border-bottom: solid 1px #888888;
	border-left: solid 1px #dddddd;
	border-top: solid 1px #dddddd;
}

.odd-row-class {
	background-color: #E2E2E2;
}

.odd-row-class td {
	font-family: verdana;
	font-size: 8pt;
	border-right: solid 1px #888888;
	border-bottom: solid 1px #888888;
	border-left: solid 1px #dddddd;
	border-top: solid 1px #dddddd;
}
</style>

	<script type="text/javascript">
	
	</script>
	</head>

	<body>
	
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<center>
			<label class="report-heading-class">
				PFizer Extension IVR Input
			</label>
		</center>
		
		<form method="post" action="PMSAngelRequestHandler">

			<br />
			

			<table align="center" border="0" cellspacing="2" cellpadding="0"
				class="input-class" width="650">

				<tr class="odd-row-class" height="30">
					<td width="50%" align="right">
						Phone Number:
					</td>
					<td width="50%">
						<input type="text" name="phoneNumber" width="50" class="input-class" maxlength="10"/>
					</td>
				</tr>


				<tr class="even-row-class" height="30">
					<td width="50%" align="right">
						Member Id : 
					</td>
					<td>
						<input type="text" name="memberId" width="50" class="input-class" maxlength="20"/>
					</td>
				</tr>

				<tr class="odd-row-class" height="30">
					<td width="50%" align="right">
						Security Key
					</td>
					<td>
						<input type="text" name="sec" width="50" class="input-class" maxlength="100">
					</td>
				</tr>
				
				<tr class="odd-row-class" height="30">
					<td width="50%" align="right">
						Next Page
					</td>
					<td>
						<input type="text" name="next_page" width="50" class="input-class" maxlength="100">
					</td>
				</tr>
                                
                                
                                <tr class="odd-row-class" height="30">
					<td width="50%" align="right">
						Trigger
					</td>
					<td>
						<input type="text" name="trigger" width="50" class="input-class" maxlength="100">
					</td>
				</tr>


				<tr class="even-row-class" height="30">
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr class="odd-row-class" height="30">
					<td>
						&nbsp;
					</td>
					<td align="left">
						<input type="submit" value=" Send " class="input-class">
					</td>
				</tr>

			</table>

		</form>
	</body>
</html>
