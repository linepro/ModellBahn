// module 'pdfViewer.js'
'use strict';


class PDFViewer {
    constructor(canvas) {
        this.pdfDoc = null;
        this.pageNum = 1;
        this.pageRendering = false;
        this.pageNumPending = null;
        this.scale = 0.8;
        this.canvas = canvas,
        this.ctx = canvas.getContext('2d');
    }

    load(file) {
        let pdfjsLib = window['pdfjs-dist/build/pdf'];

        pdfjsLib.GlobalWorkerOptions.workerSrc = siteRoot() + '/js/lib/pdf.min-2.0.943.worker.js';

        var loadingTask = pdfjsLib.getDocument(file);
        loadingTask.promise.then(pdf => this.showPage(pdf, 1))
            .catch(e => reportError(e));
    };

    async showPage(pdfDoc, pageNum) {
        this.pdfDoc = pdfDoc;
        this.pageNum = pageNum;

        pdfDoc.getPage(pageNum)
            .then(page => this.renderPage(page))
            .catch(e => reportError(e));
    }

    async renderPage(page) {
        let viewport = page.getViewport({ scale: this.scale });
        this.canvas.height = viewport.height;
        this.canvas.width = viewport.width;

        let renderContext = {
            canvasContext: this.ctx,
            viewport: viewport
        };

        return await page.render(renderContext);
    }

    prevPage() {
        this.showPage(this.pageNum <= 1 ? pageNum-- : 1);
    }

    nextPage() {
        this.showPage(this.pageNum < pdfDoc.numPages ? pageNum++ : pdfDoc.numPages);
    }
}