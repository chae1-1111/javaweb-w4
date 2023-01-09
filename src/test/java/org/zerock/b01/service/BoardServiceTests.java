package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testResister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Data")
                .content("Sample Content Data")
                .writer("chae1-1111")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info(bno);
    }

    @Test
    public void testReadOne() {

        BoardDTO boardDTO = boardService.readOne(501L);

        log.info(boardDTO);
    }

    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(501L)
                .title("Modify Test Title")
                .content("Modify Test Content")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testRemove() {
        boardService.remove(501L);

    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .type("tcw")
                .keyword("1")
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        responseDTO.getDtoList().forEach(dto -> log.info(dto));
        log.info(responseDTO.getTotal());

    }
}
