<?php
//CONTROLE

	require_once('classes/contato.class.php');

	if ($_REQUEST) {

		$contato = new contato($_REQUEST['contato']);

		switch ($_REQUEST['acao']) {

			case 'listar':
				break;

			case 'novo':
				echo $contato->novo();
				break;

			case 'update':
				break;

			case 'delete':
				break;

		}
	
	}



?>