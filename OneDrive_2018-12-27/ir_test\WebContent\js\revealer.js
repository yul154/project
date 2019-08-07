(function() {
    // Fake loading.
    setTimeout(init, 1000);

    function init() {
        document.body.classList.remove('loading');

        var scrollElemToWatch_2 = document.getElementById('rev-6'),
            watcher_2 = scrollMonitor.create(scrollElemToWatch_2, -300),
            rev6 = new RevealFx(scrollElemToWatch_2, {
                revealSettings : {
                    bgcolor: '#fcf652',
                    onCover: function(contentEl, revealerEl) {
                        contentEl.style.opacity = 1;
                    }
                }
            }),
            rev7 = new RevealFx(document.querySelector('#rev-7'), {
                revealSettings : {
                    bgcolor: '#7f40f1',
                    direction: 'rl',
                    delay: 250,
                    onCover: function(contentEl, revealerEl) {
                        contentEl.style.opacity = 1;
                    }
                }
            }),
            rev8 = new RevealFx(document.querySelector('#rev-8'), {
                revealSettings : {
                    bgcolor: '#7f40f1',
                    direction: 'rl',
                    delay: 500,
                    onCover: function(contentEl, revealerEl) {
                        contentEl.style.opacity = 1;
                    }
                }
            });

        watcher_2.enterViewport(function() {
            rev6.reveal();
            rev7.reveal();
            rev8.reveal();
            watcher_2.destroy();
        });
    }
})();