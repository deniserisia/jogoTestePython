import pygame , random
from pygame.locals import *

def on_grid_random():
    x = random.randint(0,590)
    y = random.randint(0,590)
    return ( x//10 * 10, y//10 * 10)

def collision(c1, c2):
    return (c1[0] == c2[0] and (c1[1] == c2[1])

UP = 0
RIGHT = 1
DOWN = 2
LEFT = 3

# Matriz 400 por 400
pygame.init()
screen = pygame.display.set_mode((400,400))
pygame.display.set_caption('Cobra')

# Lista de segmentos representada por uma dupla 
cobra = [(200, 200), (210, 200), (220,200)]
cobra_skin = pygame.Surface((10,10))
cobra_skin.fill((255,255,255))

maca_pos = on_grid_random()
maca = pygame.Surface((20,20))
maca.fill((255,0,0))

my_direction = LEFT

clock =pygame.time.Clock()

while True:
    clock.tick(10)
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()

        if event.type == KEYDOWN:
            if event.key == K_UP:
                my_direction = UP
            if event.key == K_DOWN:
                my_direction = DOWN
            if event.key == K_LEFT:
                my_direction = LEFT
            if event.key == K_RIGHT:
                my_direction = RIGHT

    if collision(cobra[0], maca_pos):
        maca_pos = on_grid_random()
        cobra.append((0,0))

    for i in range(len(cobra) -1, 0, -1):
        cobra[i] = (cobra[i-1][0], cobra[i-1][1])


    if my_direction == UP:
        cobra[0] = (cobra[0][0], cobra [0][1] - 10)
    if my_direction == DOWN:
        cobra[0] = (cobra[0][0], cobra [0][1] + 10)
    if my_direction == RIGHT:
        cobra[0] = (cobra[0][0] + 10, cobra [0][1])
    if my_direction == LEFT:
        cobra[0] = (cobra[0][0] - 10, cobra [0][1])

    screen.fill((0,0,0))
    screen.blit(maca, maca_pos)
    for pos in cobra:
        screen.blit(cobra_skin, pos)

    pygame.display.update()