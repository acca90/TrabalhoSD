<?php
//CONTROLE
	require_once('classes/contato.class.php');
	$request = json_decode(file_get_contents("php://input"));
	if ($request) {
		$contato = new contato($request->contato,$request->url);
		switch ($request->acao) {
			case 'listar': echo $contato->listar(); break;
			case 'novo': echo $contato->novo(); break;
			case 'update':	echo $contato->update(); break;
			case 'delete':	echo $contato->delete(); break;
		}
	}
?>