<?php
//CONTROLE

	require_once('classes/contato.class.php');

	if ($_REQUEST) {

		$contato = new contato($_REQUEST['contato']);

		switch ($_REQUEST['acao']) {

			case 'listar':
				echo $contato->listar();
				break;

			case 'novo':
				echo $contato->novo();
				break;

			case 'update':
			echo $contato->update();
				break;

			case 'delete':
			echo $contato->delete();
				break;

		}
	
	}



?>