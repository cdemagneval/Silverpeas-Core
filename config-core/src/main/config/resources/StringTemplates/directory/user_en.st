<div class="profilPhoto">
	$avatar$
 </div>
 <div class="info">
   <ul>
     <li class="userName"> <a href="$context$/Rprofil/jsp/Main?userId=$user.id$">$user.lastName$ $user.firstName$</a></li>
     <li class="infoConnection">
     	$if(user.connected)$
			$if(notMyself)$
				<a href="#" title="Talk with $user.firstName$" onclick="javascript:window.open('$context$/RcommunicationUser/jsp/OpenDiscussion?userId=$user.id$','popupDiscussion$user.id$','menubar=no, status=no, scrollbars=no, menubar=no, width=600, height=450')">
				<img src="$context$/util/icons/online.gif" alt="Talk with $user.firstName$" title="Talk with $user.firstName$"/> 
				</a>
			$else$
				<img src="$context$/util/icons/online.gif" alt="connected"/> 
			$endif$
			Online for $user.duration$
		$else$
			<img src="$context$/util/icons/offline.gif" alt="deconnected"/> Offline
        $endif$
     </li>
     $if(user.status)$
     	<li class="status">
     	  "$user.status$"
     	</li>
     $endif$
     <li class="userType">
     	$type$
     </li>
     <li class="userMail">
	<a href="#" onclick="OpenPopup($user.id$,'$user.lastName$ $user.firstName$')">$user.mail$</a>
     </li>
     <li class="extra">
     	$if(extra.title)$
     	  $extra.title$<br/>
     	$endif$
     	$if(extra.phone)$
	  Phone : $extra.phone$
	$endif$
     </li>
   </ul>
 </div>
<div class="action">
  $if(notMyself)$
  	$if(notAContact)$
	  <a href="#" class="link invitation" onclick="OpenPopupInvitaion($user.id$,'$user.lastName$ $user.firstName$');">Send an invitation</a>
	$endif$
	<a href="#" class="link notification" onclick="OpenPopup($user.id$,'$user.lastName$ $user.firstName$')">Send a message</a>
  $endif$
</div>