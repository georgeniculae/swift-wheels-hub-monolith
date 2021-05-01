$(document).ready(function(){
    // Add minus icon for collapse element which is open by default
    $(".collapse.show").each(function(){
        $(this).prev(".card-header").addClass("highlight");
    });

    // Highlight open collapsed element
    $(".card-header .btn").click(function(){
        $(".card-header").not($(this).parents()).removeClass("highlight");
        $(this).parents(".card-header").toggleClass("highlight");
    });
});