class PDFViewer {
    constructor(canvas) {
        this.pdfDoc = null;
        this.pageNum = 1;
        this.pageRendering = false;
        this.pageNumPending = null;
        this.scale = 0.8;
        this.canvas = canvas,
            this.ctx = canvas.getContext('2d');

        let pdfjsLib = window['pdfjs-dist/build/pdf'];
        pdfjsLib.GlobalWorkerOptions.workerSrc = '//mozilla.github.io/pdf.js/build/pdf.worker.js';
    }

    load(file) {
        pdfjsLib.getDocument(file)
            .then(pdfDoc => this.showPage(pdfDoc, 1))
            .catch(e => reportError(e));
    };

    async showPage(pageNum) {
        this.getPage(pdfDoc, pageNum)
            .then(page => this.render(page))
            .catch(e => reportError(e));
    }

    async getPage(pdfDoc, pageNum) {
        this.pdfDoc = pdfDoc;
        this.pageNum = pageNum;

        return await pdfDoc.getPage(page, pageNum);
    }

    async renderPage(page) {
        let viewport = page.getViewport({ scale: scale });
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        let renderContext = {
            canvasContext: ctx,
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