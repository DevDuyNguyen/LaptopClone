/*
the container for modal has class="modal"
the open modal button has attribute data-modal-target
the close modal button has class="modal-close-button"
open and close modal button are contained inside modal container

overlay with class="active" will activate the overlay
modal with class="active" will activate the modal

*/

let openModalButtons=document.querySelectorAll("[data-modal-target]");//open modal button
let overlay=document.getElementById("overlay");//overlay
let closeModalButtons=document.querySelectorAll(".modal-close-button");//close modal button



if(openModalButtons!==null && overlay!=null && closeModalButtons!==null){
	openModalButtons.forEach(button => {
	    button.addEventListener("click",()=>{
	        let modal=document.getElementById(button.getAttribute("data-modal-target"));
	        openModal(modal);
	    })
	});
	
	closeModalButtons.forEach(button=>{
	    button.addEventListener("click",()=>{
	        let modal=button.closest(".modal");
	        closeModal(modal);
	    });
	});
	
	overlay.addEventListener("click",()=>{
	    let modalList=document.querySelectorAll(".modal");//model
	    modalList.forEach((modal)=>{
	        closeModal(modal);
	    })
	});
}





function openModal(modal){
    modal.classList.add("active");
    overlay.classList.add("active");
}
function closeModal(modal){
    modal.classList.remove("active");
    overlay.classList.remove("active");
}