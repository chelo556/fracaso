<?php

$url="http://107.170.246.222/prototipo/?q=sw/rest";
//$url="http://chelo.cloudapp.net/?q=sw/rest";
//$url = 'http://localhost/drupal/sw/rest';
//
//Solo elegir uno de los  $data
//

//PAra agregar comentario, 
#$data = array('idNode' => 2, 'comentario' => 'frakaso 15');

//PAra obtener datos de un nodo (articulo)
#$data = array('idNode' => 1);

//PAra obtener datos  (index)
$data = array('index' => true);


// use key 'http' even if you send the request to https://...
$options = array(
    'http' => array(
        'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
        'method'  => 'POST',
        'content' => http_build_query($data),
    ),
);
$context  = stream_context_create($options);
$result = file_get_contents($url, false, $context);

echo $result;

#echo"<br>";
#echo"<br>";

$obj = json_decode($result,true);
//print_r( $obj);
echo utf8_decode($obj['contenido']);
echo utf8_decode($obj['mensaje']);
#var_dump($result);


?>
