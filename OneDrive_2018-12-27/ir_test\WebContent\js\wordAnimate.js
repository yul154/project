 const effects = [
    // Effect 6
    {
        options: {
            shapeColors: ['#fff','#dedede','#8c8c8c','#545454','#000','#dc2e2e']
        },
        hide: {
            lettersAnimationOpts: {
                duration: 200,
                delay: (t,i,total) => (total-i-1)*20,
                easing: 'easeOutExpo',
                opacity: {
                    value: [1,0],
                    duration: 100,
                    delay: (t,i,total) => (total-i-1)*20,
                    easing: 'linear'
                },
                scale: [1,0]
            }
        },
        show: {
            lettersAnimationOpts: {
                duration: 400,
                delay: (t,i) => i*60,
                easing: 'easeInExpo',
                opacity: [0,1],
                scale: [0,1]
            },
            shapesAnimationOpts: {
                duration: 700,
                delay: (t,i) => i*40,
                easing: 'easeOutExpo',
                translateX: () => [0,anime.random(-20,20)],
                translateY: () => [0,anime.random(-400,400)],
                scale: () => [randomBetween(0.2,0.6),randomBetween(0.2,0.6)],
                rotate: () => [0,anime.random(-16,16)],
                opacity: [
                    {value: 1, duration: 1, easing: 'linear'},
                    {value: 0, duration: 700, easing: 'easeOutQuad'}
                ]
            }
        }
    },
];

    class Slideshow {
        constructor(el) {
            this.DOM = {};
            this.DOM.el = el;
            this.DOM.words = Array.from(this.DOM.el.querySelectorAll('.word'));
            this.current = 0;
            this.words = [];
            this.DOM.words.forEach((word, pos) => {
                this.words.push(new Word(word, effects[pos].options));
        });

            this.isAnimating = true;
            this.words[this.current].show(effects[this.current].show).then(() => this.isAnimating = false);
        }
    }

    const slideshow = new Slideshow(document.querySelector('.slideshow'));