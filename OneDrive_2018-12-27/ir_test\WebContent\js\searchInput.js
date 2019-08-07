/**
 * main.js
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2016, Codrops
 * http://www.codrops.com
 */
;(function(window) {

	'use strict';

	var mainContainer = document.querySelector('.main-wrap'),
		openCtrl = document.getElementById('btn-search'),
		closeCtrl = document.getElementById('btn-search-close'),
		searchContainer = document.querySelector('.search'),
		inputSearch = searchContainer.querySelector('.search__input'),
		titles_results = "",
		currentPage = 1;

	function init() {
		initEvents();	
	}
	
	var pageClick = function(e){
		e.preventDefault();
		var page = $(this)[0].text;
		if (page == "Previous" && currentPage > 1) currentPage --;
		else if (page == "Next" && currentPage < 3) currentPage ++;
		else currentPage = page;
		console.log(currentPage);
		if (isNaN(currentPage)) alert("You have reached the page limit!");
		else {
			$("#titles").empty();
			presented_titles = "<ul style='text-align:left;'>";
			for (var i = currentPage * 10 - 10; i < currentPage * 10; i ++){
				if (titles_results[i].length == 0) continue;
				var id = titles_results[i].split("#")[1];
				var title = titles_results[i].split("#")[0];
				presented_titles = presented_titles + "<li><a class='news_item' href='GetDoc?docno=" + id + "'>" + title + "</a></li>";
			}
			presented_titles += "</ul>" +
				"<nav aria-label=\"Page navigation example\" style='margin-top: 30%;'>\n" +
                "  <ul class=\"pagination\">\n" +
                "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">Previous</a></li>\n" +
                "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>\n" +
                "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>\n" +
                "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>\n" +
                "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">Next</a></li>\n" +
                "  </ul>\n" +
                "</nav>";
			$("#titles").append(presented_titles);
		}
	}
	
	function initEvents() {
		openCtrl.addEventListener('click', openSearch);
		closeCtrl.addEventListener('click', closeSearch);
		document.addEventListener('keyup', function(ev) {
			// escape key.
			if( ev.keyCode == 27 ) {
				closeSearch();
			}
			
			if(ev.keyCode == 13){
				ev.preventDefault();
//				console.log($('#search_input').val());
				$.ajax({
					url : 'GetQuery',
					data : {
						query : $('#search_input').val()
					},
					success : function(responseText) {
						var titles, presented_titles;
						titles = responseText.split(";");
						titles_results = titles;
						presentTitle(currentPage);
						$("#page").remove();
						presented_titles = 
							"<nav aria-label=\"Page navigation example\" style='margin-top: 30%;' id='page'>\n" +
                            "  <ul class=\"pagination\">\n" +
                            "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">Previous</a></li>\n" +
                            "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>\n" +
                            "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>\n" +
                            "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>\n" +
                            "    <li class=\"page-item\"><a class=\"page-link\" href=\"#\">Next</a></li>\n" +
                            "  </ul>\n" +
                            "</nav>";
						$("#search_part").append(presented_titles);
						
						$(".page-link").click(function () {
							var page = $(this)[0].text;
							if (page == "Previous" && currentPage > 1) currentPage --;
							else if (page == "Next" && currentPage < 3) currentPage ++;
							else currentPage = page;
							console.log(currentPage);
							if (isNaN(currentPage)) alert("You have reached the page limit!");
							else {
								presentTitle(currentPage);
							}
					    });
					}
				});
			}
		});
	}

	function presentTitle(index){
		$("#titles").empty();
		let presented_titles = "<ul style='text-align:left;'>";
		for (let i = index * 10 - 10; i < index * 10; i ++){
			if (titles_results[i].length == 0) continue;
			var id = titles_results[i].split("#")[1];
			var title = titles_results[i].split("#")[0];
			presented_titles = presented_titles + "<li><a class='news_item' href='GetDoc?docno=" + id + "'>" + title + "</a></li>";
		}
		presented_titles += "</ul>";
		$("#titles").append(presented_titles);
	}

	function openSearch() {
		mainContainer.classList.add('main-wrap--hide');
		searchContainer.classList.add('search--open');
		setTimeout(function() {
			inputSearch.focus();
		}, 500);
	}

	function closeSearch() {
		mainContainer.classList.remove('main-wrap--hide');
		searchContainer.classList.remove('search--open');
		inputSearch.blur();
		inputSearch.value = '';
	}

	init();

})(window);